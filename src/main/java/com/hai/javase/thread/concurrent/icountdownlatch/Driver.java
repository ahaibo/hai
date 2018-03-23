package com.hai.javase.thread.concurrent.icountdownlatch;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//异步：无序执行
public class Driver {
    private static final int N = 10; // ...

    @Test
    public void main() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        for (int i = 0; i < N; ++i) // create and main threads
            new Thread(new Worker(startSignal, doneSignal), "T" + i).start();

        doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
        System.out.println("done!");
    }

    private void doSomethingElse() {
        System.out.println("doSomethingElse...");
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ex) {
        } // return;
    }

    public void doWork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ".doWork...");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
    }
}