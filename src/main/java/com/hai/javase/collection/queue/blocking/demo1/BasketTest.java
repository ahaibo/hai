package com.hai.javase.collection.queue.blocking.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/5/13.
 */
public class BasketTest {
    public static void main(String[] args) {

        Basket basket = new Basket();
        ExecutorService service = null;
        service=Executors.newFixedThreadPool(2);
//        service = Executors.newCachedThreadPool();

        service.execute(new Producer(basket));
        service.execute(new Consumer(basket));

        try {
            // 程序运行10s后，所有任务停止
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdownNow();
    }
}
