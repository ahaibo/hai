package com.hai.ijavase.thread.sync.lock.reentrantlock;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁ReentrantLock：锁申请无限等待且不会产生死锁
 */
public class TryLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getId() + ":Start Date:" + new Date());
        if (lock == 1) {
            toTryLock(lock1, lock2);
            return;
        } else {
            toTryLock(lock2, lock1);
        }

    }

    private void toTryLock(ReentrantLock lock1, ReentrantLock lock2) {
        while (true) {
            if (lock1.tryLock()) {
                try {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    if (lock2.tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getId() + ":My Job done");
                            System.out.println(Thread.currentThread().getId() + ":End Date:" + new Date());
                            return;
                        } finally {
                            lock2.unlock();
                        }
                    }
                } finally {
                    lock1.unlock();
                }
            }
        }
    }

    public static void main(String args[]) {
        TryLock r1 = new TryLock(1);
        TryLock r2 = new TryLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }

}
