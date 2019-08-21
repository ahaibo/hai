package com.hai.common.util.redis;

import com.hai.common.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁，全局排他锁
 */
public class RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate redisTemplate;

    public static final int TIMEOUT_ONE_HUNDRED_MSECS = 100 * 1000;
    /** 生成随机码超时时间 */
    public static final int TIMEOUT_ONE_SYSRANDOM_MSECS = 30 * 60 * 1000;
    /** 重试时间 */
    private static final int DEFAULT_ACQUIRY_RETRY_MILLIS = 100;
    /** 锁的后缀 */
    private static final String LOCK_SUFFIX = "_REDIS_LOCK";
    /** 锁的key */
    private String lockKey;
    /** 锁超时时间，防止线程在入锁以后，防止阻塞后面的线程无法获取锁 */
    private int expireMsecs = 10 * 1000;
    /** 线程获取锁的等待时间 */
    private int timeoutMsecs = 1 * 1000;
    /** 是否锁定标志 */
    private volatile boolean locked = false;
    /** 获取到锁的时间 */
    private long lockedTime = System.currentTimeMillis();

    /**
     * 构造器
     *
     * @param lockKey 锁的key
     */
    public RedisLock(String lockKey) {
        this.redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");
        this.lockKey = lockKey + LOCK_SUFFIX;
    }

    /**
     * 构造器
     *
     * @param lockKey      锁的key
     * @param timeoutMsecs 获取锁的超时时间
     */
    public RedisLock(String lockKey, int timeoutMsecs) {
        this(lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    /**
     * 构造器
     *
     * @param lockKey      锁的key
     * @param timeoutMsecs 获取锁的超时时间
     * @param expireMsecs  锁的有效期
     */
    public RedisLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        this(lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    public String getLockKey() {
        return lockKey;
    }

    /**
     * 封装和jedis方法
     *
     * @param key
     * @return
     */
    private String get(final String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj != null ? obj.toString() : null;
    }

    /**
     * 封装和jedis方法
     *
     * @param key
     * @param value
     * @return
     */
    private boolean setNX(final String key, final String value) {
        boolean result = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (this.expireMsecs > 0) {
            try {
                redisTemplate.expire(key, timeoutMsecs, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                logger.error("expire key:{} time:{} success.", key, this.timeoutMsecs);
            }
        }
        return result;
    }

    /**
     * 封装和jedis方法
     *
     * @param key
     * @param value
     * @return
     */
    private String getSet(final String key, final String value) {
        Object obj = redisTemplate.opsForValue().getAndSet(key, value);
        return obj != null ? (String) obj : null;
    }

    /**
     * 获取锁
     *
     * @return 获取锁成功返回ture，超时返回false
     * @throws InterruptedException
     */
    public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            // 锁到期时间
            String expiresStr = String.valueOf(expires);
            if (this.setNX(lockKey, expiresStr)) {
                locked = true;
                this.lockedTime = System.currentTimeMillis();
                return true;
            }
            // redis里key的时间
            String currentValue = this.get(lockKey);
            // 判断锁是否已经过期，过期则重新设置并获取
            if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis()) {
                // 设置锁并返回旧值
                String oldValue = this.getSet(lockKey, expiresStr);
                // 比较锁的时间，如果不一致则可能是其他锁已经修改了值并获取
                if (oldValue != null && oldValue.equals(currentValue)) {
                    locked = true;
                    this.lockedTime = System.currentTimeMillis();
                    return true;
                }
            }
            timeout -= DEFAULT_ACQUIRY_RETRY_MILLIS;
            // 延时
            Thread.sleep(DEFAULT_ACQUIRY_RETRY_MILLIS);
        }
        return false;
    }

    /**
     * 释放获取到的锁
     */
    public synchronized void unlock() {
        if (locked) {
            redisTemplate.delete(lockKey);
            logger.info("unlock:{} success.", lockKey);
            locked = false;
        }
    }

    /**
     * 释放获取到的锁
     * 等到获取锁时间超时后释放
     */
    public synchronized void unlockWhenTimeout() {
        if (locked) {
            long current = System.currentTimeMillis();
            long times = current - 1 - this.lockedTime - this.timeoutMsecs;
            if (times < 0) {
                //等待获取锁超时
                try {
                    Thread.sleep(Math.abs(times));
                } catch (InterruptedException e) {
                    logger.error("unlock occur error.", e);
                }
            }
            redisTemplate.delete(lockKey);
            logger.info("unlock:{} success.", lockKey);
            locked = false;
        }
    }


    /**
     * 等待过期时删除 redis key
     * 此方法一般应用在耗时较久或批量定时任务场景
     */
    public synchronized void unlockWhenExpired() {
        if (locked) {
            long current = System.currentTimeMillis();
            long times = current - 1 - this.lockedTime - this.expireMsecs;
            if (times < 0) {
                try {
                    Thread.sleep(Math.abs(times));
                } catch (InterruptedException e) {
                    logger.error("unlockWhenExpired sleep occur error,key:{}.", this.lockKey, e);
                }
            }
            redisTemplate.delete(lockKey);
            logger.info("unlockWhenExpired key:{}, expireTime:{} ms success.", lockKey, this.expireMsecs);
            locked = false;
        }
    }

}
