package com.hai.ijavase.thread.concurrent.icountdownlatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 建议130：使用CountDownLatch协调子线程
 * <p>
 * 模拟赛跑
 * <p>
 * Created by Administrator on 2017/10/2.
 */
public class Runner implements Callable<Integer> {

    private CountDownLatch begin;
    private CountDownLatch end;

    public Runner(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 10;//总共赛跑人数
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(size);

        ExecutorService service = Executors.newFixedThreadPool(size);
        Map<Integer, Future<Integer>> result = new HashMap<>();

        for (int i = 0; i < size; i++) {
            result.put(i, service.submit(new Runner(begin, end)));
        }

        //开始
        begin.countDown();
        System.out.println("running...");

        //等待全部结束
        end.await();

        System.out.println("\nfinish. results: ");
        int count = 0;
        for (Map.Entry<Integer, Future<Integer>> entry : result.entrySet()) {
            int score = entry.getValue().get();
            System.out.println(entry.getKey() + " : " + score);
            count += score;
        }
        System.out.println("total score: " + count + "; avg: " + (count / size));

        service.shutdown();

        while (!service.isTerminated()) {
            System.out.print(".");
//            TimeUnit.MILLISECONDS.sleep(200);
        }
        System.out.println("\nterminated...");
    }

    @Override
    public Integer call() throws Exception {
        begin.await();
        int score = new Random().nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(score);
        end.countDown();
        return score;
    }
}


