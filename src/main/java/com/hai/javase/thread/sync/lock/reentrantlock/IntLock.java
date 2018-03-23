package com.hai.javase.thread.sync.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * interrupted Lock
 */
public class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    /**
     * 控制加锁顺序，方便构造死锁
     *
     * @param lock
     */
    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                // lock1.lock();  
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                lock2.lockInterruptibly();
                // lock2.lock();  
                System.out.println(Thread.currentThread().getId() + ":拿到两个lock，并完成了工作");
            } else {
                lock2.lockInterruptibly();
                // lock2.lock();  
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                lock1.lockInterruptibly();
                // lock1.lock();  
                System.out.println(Thread.currentThread().getId() + ":拿到两个lock，并完成了工作");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }

    }

    public static void main(String arg[]) throws InterruptedException {

        IntLock r1 = new IntLock(1);
        IntLock r2 = new IntLock(2);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        Thread.sleep(1000);
        // interrupt one thread  
        t1.interrupt();
        // t2.interrupt();  
    }

}  