package com.hai.javase.thread.sync.lock.custom;

/**
 * 仅仅从功能上讲，上述简单版的锁实现是可以的。但是，存在如下2个问题：
 * 1. 如果某个线程连续多次调用lock()，就会自己把自己锁住。
 * 2. 如果某个线程并未执行lock()，而是直接执行unlock()，这样当前线程就可以unlock()其他线程的锁，是不合理的。
 * Created by Administrator on 2018/5/12.
 */
public class MyLockSimple {
    private boolean flag = false;

    public void lock() {
        synchronized (this) {
            while (flag) {
                try {
                    wait();// 已经加锁,当前线程需要等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = true;
        }
    }

    public void unlock() {
        synchronized (this) {
            flag = false;
            notifyAll();// 释放锁时通知其他线程
        }
    }
}
