package com.hai.javase.thread.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需求：N 个线程交替打印信息(各自打印 M 遍)，共打印 X 轮
 * 例：
 * 1.打印线程名
 * 2.N = 5, M = 10(本例为动态变化), X = 5
 * Created by Administrator on 2018/3/23.
 */
public class ThreadAlternateDemo2 {
    public static void main(String[] args) {

        int threads = 3;
        int loops = 5;
        AlternateRunnable2 alternateRunnable = new AlternateRunnable2();

        //开启 threads 个线程
        for (int i = 1; i <= threads; i++) {
            final int temp = i;//当前线程

            new Thread(new Runnable() {//每个线程有各自的业务处理，分别开 Runnable 实现业务

                @Override
                public void run() {
                    for (int j = 1; j <= loops; j++) {//每个线程打印 loops 轮
                        int count = temp;
                        switch (temp) {
                            case 1:
                                alternateRunnable.print1(count, j);
                                break;
                            case 2:
                                alternateRunnable.print2(count, j);
                                break;
                            case 3:
                                alternateRunnable.print3(count, j);
                                break;
                        }
                        System.out.println();
                    }
                }
            }, "线程" + i).start();
        }
    }
}

class AlternateRunnable2 {

    //线程标识：1-线程1；2-线程2；3-线程3
    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public AlternateRunnable2() {
    }

    public void print1(int count, int curr) {
        printHandle(count, curr, 1, condition1, condition2);
    }

    public void print2(int count, int curr) {
        printHandle(count, curr, 2, condition2, condition3);
    }

    public void print3(int count, int curr) {
        printHandle(count, curr, 3, condition3, condition1);
    }

    private void printHandle(int count, int curr, int flag, Condition await, Condition signal) {
        lock.lock();
        try {
            //不为指定线程则等待
            if (this.flag != flag) {
                try {
                    await.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印
            print(count, curr);

            //改变 flag 值，切换线程执行
            changeFlag();

            //唤醒指定线程
            signal.signal();
        } finally {
            lock.unlock();
        }
    }

    private void changeFlag() {
        this.flag = (flag == 1) ? 2 : (flag == 2 ? 3 : 1);
    }

    private void print(int count, int curr) {
        for (int i = 1; i <= count; i++) {
            System.out.println(Thread.currentThread().getName() + " " + curr + "." + i);
        }
    }
}
