package com.hai.javase.concurrent.complete;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {

    private static final int TASK_NUM = 20;
    private static final Random RANDOM = new Random();
    private static final int RANGE = 1000;

    public static void main(String[] args) {
        CompletionServiceTest completionServiceTest = new CompletionServiceTest();
        completionServiceTest.count1();
        completionServiceTest.count2();
    }

    public void count1() {
        ExecutorService service = Executors.newCachedThreadPool();
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < TASK_NUM; i++) {
            Future<Integer> result = service.submit(new CountTask(RANDOM, RANGE));
            queue.add(result);
        }

        int sum = reduceCount(queue);
        System.out.println("count1 sum: " + sum);

        service.shutdown();
    }

    public void count2() {
        ExecutorService service = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService(service);
        for (int i = 0; i < TASK_NUM; i++) {
            completionService.submit(new CountTask(RANDOM, RANGE));
        }

        int sum = 0;
        for (int i = 0; i < TASK_NUM; i++) {
            try {
                sum += completionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count2 sum: " + sum);

        service.shutdown();
    }

    private int reduceCount(BlockingQueue<Future<Integer>> queue) {
        if (null == queue || queue.isEmpty()) {
            System.out.println("queue is empty...");
            return 0;
        }
        int sum = 0;
        for (int i = 0, size = queue.size(); i < size; i++) {
            try {
                sum += queue.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    class CountTask implements Callable<Integer> {
        private int range;
        private Random random;

        public CountTask(Random random, int range) {
            this.random = random;
            this.range = range;
        }

        @Override
        public Integer call() {
            return this.random.nextInt(this.range) + this.random.nextInt(this.range);
        }
    }
}
