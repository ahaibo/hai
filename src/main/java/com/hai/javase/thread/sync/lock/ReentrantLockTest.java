package com.hai.javase.thread.sync.lock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2017/12/6.
 */
public class ReentrantLockTest {
    private ReentrantLock reentrantLock = new ReentrantLock();
    private static Random random = new Random();

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            int tmp = i;
            executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    reentrantLockTest.test();
                    return random.nextInt(1000) + 1;
                }
            });
        }
        executorService.shutdown();
    }

    @Test
    public void test() {
        try {
            reentrantLock.tryLock(2, TimeUnit.SECONDS);
            business();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void business() {
        String threadName = "thread " + Thread.currentThread().getName();
        System.out.println(threadName + "\tbusiness start...");
        Random random = new Random();
        try {
            int time = random.nextInt(5000) + 100;
            System.out.println(threadName + "\toperation time: " + time);
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + "\tbusiness end...");
    }
}
