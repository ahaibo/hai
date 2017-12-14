package com.hai.idb;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    public static AtomicInteger count = new AtomicInteger();

    public static void inc() {
        count.getAndIncrement();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        Counter.inc();
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("运行结果:Counter.count=" + Counter.count);

    }
}