package com.hai.test;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/6.
 */
public class ScheduledExecutorServiceTest {

    @Test
    public void test1() {
        ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
        pool.scheduleWithFixedDelay(() -> {
            System.out.println("doSomeTask");
        }, 1, 1, TimeUnit.SECONDS);
    }

    public void test2() {
        CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("doSomeTask...");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            return null;
        });
    }


    public static void main(String[] args) {
        ScheduledExecutorServiceTest instance = new ScheduledExecutorServiceTest();
        instance.test1();
        instance.test2();
    }
}
