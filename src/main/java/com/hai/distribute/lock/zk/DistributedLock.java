package com.hai.distribute.lock.zk;

import java.util.concurrent.TimeUnit;

/**
 * 基于Zookeeper的分布式锁
 * https://blog.csdn.net/sunfeizhi/article/details/51926396
 * Created by Administrator on 2018/5/26.
 */
public interface DistributedLock {

    //获取锁，如果没有得到就等待
    void acquire() throws Exception;

    /**
     * 获取锁，直到超时
     *
     * @param time
     * @param unit
     * @throws Exception
     */
    boolean acquire(long time, TimeUnit unit) throws Exception;

    //释放锁
    void release() throws Exception;
}
