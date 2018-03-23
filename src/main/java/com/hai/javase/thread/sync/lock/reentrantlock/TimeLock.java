package com.hai.javase.thread.sync.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁ReentrantLock：锁申请等待限时
 */
public class TimeLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getId() + ":get lock successed!");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getId() + ":get lock failed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println(Thread.currentThread().getId() + ":unlock");
                lock.unlock();
            }
        }
    }

    public static void main(String args[]) {
        TimeLock tl = new TimeLock();

        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);

        t1.start();
        t2.start();
    }
}  