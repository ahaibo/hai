package com.hai.javase.thread.juc;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2018/3/23.
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.write(new Random().nextInt(999));
            }
        }, "Write: ").start();

        for (int i = 1; i <= 9; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.read();
                }
            }, "Read" + i).start();
        }
    }
}

class ReadWriteLockDemo {
    private int value;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public int read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " value: " + this.value);
        } finally {
            lock.readLock().unlock();
        }
        return this.value;
    }

    public void write(int value) {
        lock.writeLock().lock();
        try {
            this.value = value;
            System.out.println(Thread.currentThread().getName() + " value: " + value + "\n");
        } finally {
            lock.writeLock().unlock();
        }
    }
}
