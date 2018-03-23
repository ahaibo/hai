package com.hai.javase.thread.juc.communication.procon;

/**
 * Created by Administrator on 2018/3/23.
 */
public class Clerk3 implements IClerk{
    private int product = 0;

    public synchronized void add() {
        while (product >= 10) {//解决虚假唤醒, 条件判断总是在循环中
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + ++product);
        this.notifyAll();
    }

    public synchronized void sale() {
        while (product <= 0) {
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : " + --product);
        this.notifyAll();
    }
}
