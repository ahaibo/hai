package com.hai.javase.thread.concurrent.cyclicbarrier;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 建议131: cyclicbarrier 让多线程齐步走
 * Created by Administrator on 2017/10/2.
 */
public class WorkerExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int size = 10;

        ExecutorService service = Executors.newFixedThreadPool(size);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(size, new Runnable() {
            @Override
            public void run() {
                System.out.println("\nall work done!");
            }
        });

        System.out.println("working...");
        List<Future<WorkInfo>> futures = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Worker task = new Worker(i, "T" + i, cyclicBarrier);
            futures.add(service.submit(task));
        }

        service.shutdown();
        while (!service.isTerminated()) {
            System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("\nterminated!");

        System.out.println("\nwork info:");
        for (Future<WorkInfo> future : futures) {
            System.out.println(future.get());
        }
    }

    static class Worker implements Callable<WorkInfo> {
        private int id;
        private String name;
        private CyclicBarrier cyclicBarrier;

        public Worker(int id, String name, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.name = name;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public WorkInfo call() throws Exception {
            System.out.println("Thread " + name + " working...");
            int score = new Random().nextInt(100);
            Thread.sleep(score * 20);
            cyclicBarrier.await();
            return new WorkInfo(this.id, this.name, score);
        }
    }

    static class WorkInfo {
        private int id;
        private String name;
        private int score;

        public WorkInfo(int id, String name, int score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}


