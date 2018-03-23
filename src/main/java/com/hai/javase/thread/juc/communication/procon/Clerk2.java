package com.hai.javase.thread.juc.communication.procon;

/**
 * 此案例可解决线程不能被唤醒的问题，但存在虚假唤醒的问题
 * Created by Administrator on 2018/3/23.
 */
public class Clerk2 implements IClerk{
    private int product = 0;

    public synchronized void add() {
        if (product >= 10) {//存在虚假唤醒的问题
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
        if (product <= 0) {//存在虚假唤醒的问题
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
