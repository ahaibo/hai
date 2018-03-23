package com.hai.javase.thread.sync.lock.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": read lock:");
            condition.await(); // release lock，thread is waiting()  
            System.out.println(Thread.currentThread().getName() + ": is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + ": release lock");
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);

        lock.lock();
        System.out.println(Thread.currentThread().getName() + ": read lock:");
        condition.signal();
        System.out.println(Thread.currentThread().getName() + ": notify:");
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + ": release lock");
    }
}  