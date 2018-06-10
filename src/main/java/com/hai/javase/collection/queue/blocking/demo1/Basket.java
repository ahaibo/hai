package com.hai.javase.collection.queue.blocking.demo1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 定义装苹果的篮子
 * Created by Administrator on 2018/5/13.
 */
public class Basket {
    //装苹果的阻塞有界队列
    private BlockingQueue<String> baskets = new ArrayBlockingQueue<String>(5);

    public void produce() throws InterruptedException {
        baskets.put("An Apple." + System.nanoTime());
    }

    public String consumer() throws InterruptedException {
        return baskets.take();
    }

    public int getAppleNumber() {
        return baskets.size();
    }
}
