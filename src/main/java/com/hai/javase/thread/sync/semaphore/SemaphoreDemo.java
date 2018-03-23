package com.hai.javase.thread.sync.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable {

    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000); //模拟耗时操作  
            System.out.println(Thread.currentThread().getName() + ": done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        final SemaphoreDemo semaphoreDemo = new SemaphoreDemo();

        for (int index = 0; index < 20; index++) {
            executorService.submit(semaphoreDemo);
        }
        executorService.shutdown();
    }

}  