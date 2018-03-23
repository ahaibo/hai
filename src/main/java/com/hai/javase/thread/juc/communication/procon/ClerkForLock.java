package com.hai.javase.thread.juc.communication.procon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock + Condition 实现同步并线程间通信，无虚假线程唤醒问题
 * Created by Administrator on 2018/3/23.
 */
public class ClerkForLock implements IClerk {
    private int product = 0;
    private Lock lock = new ReentrantLock();
            private Condition condition = lock.newCondition();

        public void add() {
            lock.lock();
            try {
                while (product >= 10) {//解决虚假唤醒, 条件判断总是在循环中
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            System.out.println("产品已满");
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("缺货");
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
