package com.hai.javase.thread.deadlock;

/**
 * @author Administrator
 */
public class DeadlockRisk {
    private static class Resource {
        private int value;
    }

    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    public void setThreadName(String threadName) {
        Thread.currentThread().setName(threadName);
    }

    /**
     * 模拟死锁的读方法
     */
    public int read() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + ".read() 方法获取了resourceA的锁！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + ".read() 方法获取了resourceB的锁！");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return resourceA.value + resourceB.value;
            }
        }
    }

    /**
     * 模拟死锁的写方法
     *
     * @param a
     * @param b
     */
    public void write(int a, int b) {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + ".writ() 方法获取了resourceA的锁！");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + ".writ() 方法获取了resourceB的锁！");
                resourceA.value = a;
                resourceB.value = b;
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
