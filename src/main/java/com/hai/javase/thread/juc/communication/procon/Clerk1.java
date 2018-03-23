package com.hai.javase.thread.juc.communication.procon;

/**
 * 此案例存在等待不能唤醒的问题
 * Created by Administrator on 2018/3/23.
 */
public class Clerk1 implements IClerk{
    private int product = 0;

    public synchronized void add() {
        if (product >= 10) {
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {//可能等待不能被唤醒
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            this.notifyAll();
        }
    }

    public synchronized void sale() {
        if (product <= 0) {
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {//可能等待不能被唤醒
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            this.notifyAll();
        }
    }
}
