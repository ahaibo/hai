package com.hai.javase.thread.threadpool.iexecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author Administrator
 */
public class CustomThreadPool {

    public static void main(String[] args) {

        //每个线程进行计算的次数：百万
        int depleteCalcCount = 1000000;

        // 池中所保存的线程数，包括空闲线程。
        int corePoolSize = 5;

        // 池中允许的最大线程数。
        int maximumPoolSize = 1000;

        // 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
        long keepAliveTime = 2;

        // 参数的时间单位。
        TimeUnit unit = TimeUnit.MINUTES;

        // 执行前用于保持任务的队列。此队列仅保持由 action 方法提交的 Runnable 任务。
        int runnableQueueCapacity = maximumPoolSize * 2;

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(runnableQueueCapacity);

        // 执行程序创建新线程时使用的工厂。
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };

        // 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.getClass().getName() + ".rejectedExecution!");
            }
        };

        ThreadPoolExecutor customThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit, workQueue, threadFactory, handler);

        List<Runnable> threadList = new ArrayList<>();
        for (int i = 0; i < maximumPoolSize; i++) {
            boolean isOdd = i % 2 != 0;
            threadList.add(new MyExecutorsTestRunnable("线程" + (i + 1), true));
        }

        long start1 = System.nanoTime();
        for (int i = 0; i < threadList.size(); i++) {
            // 将线程放入池中进行执行
//            customThreadPoolExecutor.action(threadList.read(i));
            customThreadPoolExecutor.submit(threadList.get(i));
        }

        long start2 = System.nanoTime();
        // 关闭线程池
        customThreadPoolExecutor.shutdown();

        while (!customThreadPoolExecutor.isTerminated()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();
        long time1 = end - start1;
        long time2 = end - start2;
        long diffTimes = time1 - time2;

        logInfo(corePoolSize, maximumPoolSize, keepAliveTime, unit, runnableQueueCapacity, depleteCalcCount, time1, time2, diffTimes);
    }

    private static void logInfo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int runnableQueueCapacity, int depleteCalcCount, long time1, long time2, long diffTimes) {

        StringBuilder info = new StringBuilder(200);

        info.append("\nall threads execution terminated!");

        //ThreadPoolExecutor info
        info.append("\n\nThreadPoolExecutor info:");
        info.append("\n\tcorePoolSize: " + corePoolSize);
        info.append("\n\tmaximumPoolSize: " + maximumPoolSize);
        info.append("\n\tkeepAliveTime: " + keepAliveTime);
        info.append("\n\tunit: " + unit);
        info.append("\n\trunnableQueueCapacity: " + runnableQueueCapacity);
        info.append("\n\tdepleteCalcCount: " + depleteCalcCount);

        //time infos
        info.append("\n\nused times:");
        info.append("\n\ttime1: ").append(time1);
        info.append("\n\ttime2: ").append(time2);
        info.append("\n\tdiff times(time1 - time2) nanoseconds: ").append(diffTimes);
        info.append(";\t").append(TimeUnit.MILLISECONDS.convert(diffTimes, TimeUnit.MILLISECONDS)).append(" ms.");
    }


}
