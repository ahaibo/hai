package com.hai.javase.thread.juc.communication.procon;

/**
 * 线程通信：生产者 消费者模式案例
 * Created by Administrator on 2018/3/23.
 */
public class App {
    public static void main(String[] args) {
        IClerk clerk = null;
//        clerk = new Clerk1();
//        clerk = new Clerk2();
        clerk = new ClerkForLock();

        Producer pro = new Producer(clerk);
        Consumer cus = new Consumer(clerk);

        new Thread(pro, "生产者 1").start();
        new Thread(cus, "消费者 1").start();

        new Thread(pro, "生产者 2").start();
        new Thread(cus, "消费者 2").start();
//        new Thread(cus, "消费者 3").start();
    }
}
