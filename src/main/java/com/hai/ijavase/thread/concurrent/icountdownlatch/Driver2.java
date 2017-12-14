package com.hai.ijavase.thread.concurrent.icountdownlatch;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//同步：有序执行
public class Driver2 {
    private static final int N = 10; // ...

    @Test
    public void main() throws InterruptedException {
        CountDownLatch doneSignal = new CountDownLatch(N);
        ExecutorService e = Executors.newCachedThreadPool();

        for (int i = 0; i < N; ++i) // create and start threads
            e.execute(new WorkerRunnable(doneSignal, i));

        doneSignal.await();           // wait for all to finish

        e.shutdown();
        while (!e.isTerminated()) {
            System.out.print(".");
        }
        System.out.println("\ndone!");
    }
}

class WorkerRunnable implements Runnable {
    private final CountDownLatch doneSignal;
    private final int i;

    public WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    public void run() {
        doWork(i);
        doneSignal.countDown();
    }

    public void doWork(int i) {
        System.out.println(Thread.currentThread().getName() + ".doWork. i = " + i);
    }
}