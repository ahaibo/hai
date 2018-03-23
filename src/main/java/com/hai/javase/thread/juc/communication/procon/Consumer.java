package com.hai.javase.thread.juc.communication.procon;

/**
 * Created by Administrator on 2018/3/23.
 */
public class Consumer implements Runnable {

    private IClerk clerk;

    public Consumer(IClerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
