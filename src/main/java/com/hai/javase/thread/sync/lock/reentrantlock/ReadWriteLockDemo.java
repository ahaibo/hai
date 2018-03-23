package com.hai.javase.thread.sync.lock.reentrantlock;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2018/2/2.
 */
public class ReadWriteLockDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock(); // 模拟读操作
            Thread.sleep(1000); // 读操作的耗时越多，ReadWriteLock的优势就越明显
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock(); // 模拟写操作
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String args[]) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();

        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleRead(readLock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Read Thread End " + Thread.currentThread().getName() + ":" + new Date());
            }

        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Write Thread End " + Thread.currentThread().getName() + ":" + new Date());
            }

        };

        System.out.println("Starting:" + new Date());
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }

        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }

    }
}
