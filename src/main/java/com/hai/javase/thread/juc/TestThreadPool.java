package com.hai.javase.thread.juc;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 一、线程池：提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应的速度。
 * <p>
 * 二、线程池的体系结构：
 * java.util.concurrent.Executor : 负责线程的使用与调度的根接口
 * |--**ExecutorService 子接口: 线程池的主要接口
 * |--ThreadPoolExecutor 线程池的实现类
 * |--ScheduledExecutorService 子接口：负责线程的调度
 * |--ScheduledThreadPoolExecutor ：继承 ThreadPoolExecutor， 实现 ScheduledExecutorService
 * <p>
 * 三、工具类 : Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池。线程池中只有一个线程
 * <p>
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务。
 * Created by Administrator on 2018/3/23.
 */
public class TestThreadPool {
    private static Long count = 100000000 * 10L;

    @Test
    public void maxValue() {
        System.out.println("Integer.MAX_VALUE:\t" + Integer.MAX_VALUE);
        System.out.println("Long.MAX_VALUE:\t\t" + Long.MAX_VALUE);
        System.out.println("Float.MAX_VALUE:\t" + Float.MAX_VALUE);
        System.out.println("Double.MAX_VALUE:\t" + Double.MAX_VALUE);
    }

    public static void main(String[] args) {

        Instant start = Instant.now();

        ExecutorService service = Executors.newFixedThreadPool(5);
        List<Future<Long>> results = new ArrayList<>();

        TestThreadPool instance = new TestThreadPool();
        for (int i = 1; i <= 9; i++) {
            //有返回值
            Future<Long> future = service.submit(instance.new SumCallable("T" + i));
            results.add(future);
        }


        for (Future<Long> result : results) {
            try {
                result.get(50, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

//        Demo demo = new TestThreadPool().new Demo("R1");
//        for (int i = 0; i < 10; i++) {
//            service.submit(demo);
//        }

        Instant end = Instant.now();
        System.out.println("Duration time: " + Duration.between(start, end).toMillis());

        service.shutdown();
    }

    class SumCallable implements Callable<Long> {

        private String name;

        public SumCallable(String name) {
            this.name = name;
        }

        @Override
        public Long call() throws Exception {
            return TestThreadPool.sum(this.name);
        }
    }

    //无返回值
    class Demo implements Runnable {

        private String name;

        public Demo(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            TestThreadPool.sum(this.name);
        }
    }

    private static Long sum(String name) {
        Long sum = 0L;
        for (Long i = 1L; i <= count; i++) {
            sum += i;
        }
        System.out.println(name + " Sum: " + sum);
        return sum;
    }
}


