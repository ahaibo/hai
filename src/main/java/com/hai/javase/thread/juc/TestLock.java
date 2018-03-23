package com.hai.javase.thread.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/3/23.
 */
public class TestLock {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Ticket ticket = new Ticket();

        for (int i = 1; i <= 3; i++) {
//            new Thread(ticket, i + "号窗口").start();
            executorService.execute(ticket);
        }

        executorService.shutdown();

//        new Thread(ticket, "1号窗口").start();
//        new Thread(ticket, "2号窗口").start();
//        new Thread(ticket, "3号窗口").start();

    }
}

class Ticket implements Runnable {
    private int ticket = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " ticket: " + --ticket);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
