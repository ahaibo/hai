package com.hai.javase.thread.sync.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 一般用于(条件)阻塞队列，场景逻辑
 * ArrayBlockingQueue 的实现原理
 * Created by Administrator on 2017/12/6.
 */
public class ConditionTest {

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    private Object[] items = new Object[100];
    int putIndex, takeIndex, count;

    public void put(Object val) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putIndex] = val;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take(String key) throws InterruptedException {
        lock.lock();
        Object val = null;
        try {
            while (count == 0) {
                notEmpty.await();
            }
            val = items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            --takeIndex;
            notFull.signal();
            return val;
        } finally {
            lock.unlock();
        }
    }
}
