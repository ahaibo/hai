package com.hai.test;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.*;

public class TestThreadPoolExecutor {


    private static int coreSize = Runtime.getRuntime().availableProcessors();
    private static int maxSize = coreSize * 2;
    private static long keepAliveTime = 120;
    private static TimeUnit unit = TimeUnit.SECONDS;

    private static ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, unit, new LinkedBlockingDeque<>());
    private static ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, unit, new LinkedBlockingDeque<>());
    private static ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, unit, new LinkedBlockingDeque<>());
    private static ThreadPoolExecutor threadPoolExecutor4 = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, unit, new LinkedBlockingDeque<>());
    private static ThreadPoolExecutor threadPoolExecutor5 = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, unit, new LinkedBlockingDeque<>());

    private static Random random = new Random();
    private static int size = 100000;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        testThreadPoolExecutor1();
        testThreadPoolExecutor2();
        testThreadPoolExecutor3();
        testThreadPoolExecutor4();
        testThreadPoolExecutor5();
        long end = System.currentTimeMillis();
        System.out.println("used times:" + (end - start) + " ms");
    }

    public static void testThreadPoolExecutor1() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threadPoolExecutor1.execute(() -> {
                int num = random.nextInt(10) + 2;
                System.out.println("testThreadPoolExecutor1 sleep times:" + num);
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void testThreadPoolExecutor2() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threadPoolExecutor2.execute(() -> {
                int num = random.nextInt(10) + 2;
                System.out.println("testThreadPoolExecutor2 sleep times:" + num);
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void testThreadPoolExecutor3() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threadPoolExecutor3.execute(() -> {
                int num = random.nextInt(10) + 2;
                System.out.println("testThreadPoolExecutor3 sleep times:" + num);
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void testThreadPoolExecutor4() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threadPoolExecutor4.execute(() -> {
                int num = random.nextInt(10) + 2;
                System.out.println("testThreadPoolExecutor4 sleep times:" + num);
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void testThreadPoolExecutor5() throws InterruptedException {
        for (int i = 0; i < size; i++) {
            threadPoolExecutor5.execute(() -> {
                int num = random.nextInt(10) + 2;
                System.out.println("testThreadPoolExecutor5 sleep times:" + num);
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
