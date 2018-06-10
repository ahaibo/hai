package com.hai.javase.thread.sync.lock.custom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义锁
 * 解决 com.hai.javase.thread.sync.lock.custom.MyLockSimple 存在的问题
 * 为了解决这两个问题，还应该进一步做如下处理：
 * 1. 如果线程已经加锁，则不需要再加锁，避免自己把自己锁住。
 * 2. 如果线程自己并未加锁，则不能释放锁，避免未获得锁的线程释放其他线程的锁。
 * Created by Administrator on 2018/5/12.
 */
public class MyLockGood {
    private AtomicInteger locks = new AtomicInteger(0);
    private volatile Thread owner = null;

    public void lock() {
        synchronized (this) {
            Thread me = Thread.currentThread();
            while (locks.get() > 0 && owner != me) {
                try {
                    // 如果其他线程已经加锁,且不是当前线程自己加的锁,则等待
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (owner == me && locks.get() > 0) {
                return;
            }

            owner = me;
            locks.compareAndSet(locks.get(), locks.get() + 1);
        }
    }

    public void unlock() {
        synchronized (this) {
            Thread me = Thread.currentThread();
            // 没有加锁,或者当前加锁的线程不是自己,不需要解锁
            if (locks.get() == 0 || me != owner) {
                return;
            }

            locks.compareAndSet(locks.get(), locks.get() - 1);
            owner = null;
            notifyAll();
        }
    }
}
