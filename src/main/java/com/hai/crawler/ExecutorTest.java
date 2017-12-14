package com.hai.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 模拟 Executor 在爬虫中的应用
 */
public class ExecutorTest {

    private static Integer pages = 1; // 网页数
    private static boolean exeFlag = true; // 执行标识

    public static void main(String[] args) {
        //Executor在管理多个线程的时候，会进行有效的安排处理，比如创建的时候，线程池是10个，假如实际线程超过10个，Executor会进行有效的队列阻塞和调度。对我们开发者这是透明的，完全不用关心它内部的具体执行
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建ExecutorService 连接池创建固定的10个初始线程

        while (exeFlag) {
            if (pages <= 100) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        System.out.println("爬取了第" + pages + "网页...");
                        pages++;
                    }
                });
            } else {
                if (((ThreadPoolExecutor) executorService).getActiveCount() == 0) { // 活动线程是0
                    executorService.shutdown(); // 结束所有线程
                    exeFlag = false;
                    System.out.println("爬虫任务已经完成");
                }
            }
            try {
                Thread.sleep(100); // 线程休息0.1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}