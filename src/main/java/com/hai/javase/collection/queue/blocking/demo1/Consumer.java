package com.hai.javase.collection.queue.blocking.demo1;

/**
 * Created by Administrator on 2018/5/13.
 */
public class Consumer implements Runnable {

    private Basket basket;

    public Consumer(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("消费者准备生产苹果......");
                basket.consumer();
                System.out.println("消费者生产苹果完毕......");
                System.out.println("消费完后有苹果：" + basket.getAppleNumber() + "个");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
