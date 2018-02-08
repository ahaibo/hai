package com.hai.ijavase.thread.sync.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁ReentrantLock：公平锁
 */
public class FairLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock!");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String args[]) {

        FairLock r1 = new FairLock();

        Thread t1 = new Thread(r1, "T1");
        Thread t2 = new Thread(r1, "T2");
        Thread t3 = new Thread(r1, "T3");

        t1.start();
        t2.start();
        t3.start();
    }

}  