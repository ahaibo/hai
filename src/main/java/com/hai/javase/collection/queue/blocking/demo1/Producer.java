package com.hai.javase.collection.queue.blocking.demo1;

/**
 * Created by Administrator on 2018/5/13.
 */
public class Producer implements Runnable {

    private Basket basket;

    public Producer(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("生产者准备生产苹果......");
                basket.produce();
                System.out.println("生产者生产苹果完毕......");
                System.out.println("生产完后有苹果：" + basket.getAppleNumber() + "个");
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
