package com.hai.iguava;

import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestRateLimiter {

    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Random random = new Random(10);
        RateLimiterDemo demo = new RateLimiterDemo();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    Thread.sleep(random.nextInt(1000));
                    demo.doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.countDown();
//        System.in.read();
    }

    static class RateLimiterDemo {
        RateLimiter rateLimiter = RateLimiter.create(10);

        public void doSomething() {
            if (rateLimiter.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + "-正确执行");
            } else {
                System.out.println(Thread.currentThread().getName() + "-执行失败");
            }
        }
    }
}
