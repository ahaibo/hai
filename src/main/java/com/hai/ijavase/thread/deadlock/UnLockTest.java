package com.hai.ijavase.thread.deadlock;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnLockTest {
    public static final Semaphore obj1 = new Semaphore(1);
    public static final Semaphore obj2 = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(new LockAa()).start();
        new Thread(new LockBb()).start();
    }
}

class LockAa implements Runnable {
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockA 开始执行");
            while (true) {
                if (UnLockTest.obj1.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(new Date().toString() + " LockA 锁住 obj1");
                    if (UnLockTest.obj2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockA 锁住 obj2");
                        Thread.sleep(60 * 1000); // do something
                    } else {
                        System.out.println(new Date().toString() + "LockA 锁 obj2 失败");
                    }
                } else {
                    System.out.println(new Date().toString() + "LockA 锁 obj1 失败");
                }
                UnLockTest.obj1.release(); // 释放
                UnLockTest.obj2.release();
                Thread.sleep(1000); // 马上进行尝试，现实情况下do something是不确定的
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LockBb implements Runnable {
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockB 开始执行");
            while (true) {
                if (UnLockTest.obj2.tryAcquire(1, TimeUnit.SECONDS)) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj2");
                    if (UnLockTest.obj1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj1");
                        Thread.sleep(60 * 1000); // do something
                    } else {
                        System.out.println(new Date().toString() + "LockB 锁 obj1 失败");
                    }
                } else {
                    System.out.println(new Date().toString() + "LockB 锁 obj2 失败");
                }
                UnLockTest.obj1.release(); // 释放
                UnLockTest.obj2.release();
                Thread.sleep(10 * 1000); // 这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}