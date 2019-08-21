package com.hai.common.util.redis;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * RedissonLockUtil
 */
public class RedissonLockUtil {

    public static final Logger logger = LoggerFactory.getLogger(RedissonLockUtil.class);
    /** 最多等待时间 */
    public static final long REDISSON_LOCK_WAIT_TIME = 10L;
    /** 锁释放时间 */
    public static final long REDISSON_LOCK_LEASE_TIME = 100L;
    /** 需要大量计算或耗时场景的过期时间 */
    public static final long REDISSON_LOCK_LEASE_TIME_ONE_HOURS = 60 * 60L;
    /** OSS文件上传超时时间 */
    public static final long REDISSON_LOCK_LEASE_TIME_OSS_UPLOAD = 60 * 20L;
    /** 时间单位 */
    public static final TimeUnit UNIT = TimeUnit.SECONDS;

    private static RedissonClient redissonClient;

    public void setRedissonClient(RedissonClient redissonClient) {
        RedissonLockUtil.redissonClient = redissonClient;
    }

    /**
     * 加锁
     *
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    public static void unlock(String lockKey) {
        unlock(redissonClient.getLock(lockKey));
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public static void unlock(RLock lock) {
        if (null != lock) {
            lock.unlock();
        }
    }

    public static void unWritelock(String key) {
        unWritelock(redissonClient.getReadWriteLock(key));
        logger.info("unWritelock key:{} success.", key);
    }

    public static void unWritelock(RReadWriteLock lock) {
        if (null != lock) {
            lock.writeLock().unlock();
        }
    }

    public static void unReadlock(String key) {
        redissonClient.getReadWriteLock(key).readLock().unlock();
        logger.info("unReadlock key:{} success.", key);
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public static RLock lock(String lockKey, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, TimeUnit.SECONDS);
        return lock;
    }

    /**
     * 带超时的锁
     *
     * @param lockKey
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    public static RLock lock(String lockKey, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    public static boolean tryWriteLock(String key) {
        return tryWriteLock(key, REDISSON_LOCK_LEASE_TIME);
    }

    public static boolean trySysRandomWriteLock(String key) {
        return tryWriteLock(key, 0, REDISSON_LOCK_LEASE_TIME_ONE_HOURS);
    }

    public static boolean tryWriteLock(String key, long leaseTime) {
        return tryWriteLock(key, REDISSON_LOCK_WAIT_TIME, leaseTime);
    }

    public static boolean tryWriteLock(String key, long waitTime, long leaseTime) {
        return tryWriteLock(key, waitTime, leaseTime, UNIT);
    }

    public static boolean tryWriteLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        logger.info("key:{} tryWriteLock. waitTime: {}; leaseTime: {}.", key, waitTime, leaseTime);
        try {
            boolean result = redissonClient.getReadWriteLock(key).writeLock().tryLock(waitTime, leaseTime, unit);
            logger.info("key:{} tryWriteLock. waitTime: {}; leaseTime: {}. result:{}", key, waitTime, leaseTime, result);
            return result;
        } catch (InterruptedException e) {
            logger.info("key:{} tryWriteLock occur error!", key, e);
            return false;
        }
    }

    public static boolean trySysRandomReadLock(String key) {
        return tryReadLock(key, 0, REDISSON_LOCK_LEASE_TIME_ONE_HOURS, UNIT);
    }

    public static boolean tryReadLock(String key) {
        return tryReadLock(key, REDISSON_LOCK_WAIT_TIME, REDISSON_LOCK_LEASE_TIME, UNIT);
    }

    public static boolean tryReadLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        logger.info("key:{} tryReadLock. waitTime:{}; leaseTime:{}.", key, waitTime, leaseTime);
        try {
            boolean result = redissonClient.getReadWriteLock(key).readLock().tryLock(waitTime, leaseTime, unit);
            logger.info("key:{} tryReadLock. waitTime:{}; leaseTime:{}. result:{}", key, waitTime, leaseTime, result);
            return result;
        } catch (InterruptedException e) {
            logger.info("key:{} tryReadLock occur error!", key, e);
            return false;
        }
    }
}