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
public class ThreadAlternateDemo {
    public static void main(String[] args) {

        int threads = 3;
        int loops = 5;
        AlternateRunnable alternateRunnable = new AlternateRunnable();

        //开启 threads 个线程
        for (int i = 1; i <= threads; i++) {
            final int temp = i;//当前线程

            new Thread(new Runnable() {//每个线程有各自的业务处理，分别开 Runnable 实现业务
                @Override
                public void run() {
                    for (int j = 1; j <= loops; j++) {//每个线程打印 loops 轮
                        alternateRunnable.run(temp, j);
                    }
                }
            }, "线程" + i).start();
        }
    }
}

class AlternateRunnable {

    private int flag = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public AlternateRunnable() {
    }

    /**
     * 运行程序
     *
     * @param count 打印次数
     * @param curr  当前第几轮，外层循环
     */
    public void run(int count, int curr) {
        lock.lock();
        try {
            switch (flag) {
                case 1:
                    handle(count, curr, 1, condition1, condition2);
                    break;
                case 2:
                    handle(count, curr, 2, condition2, condition3);
                    break;
                case 3:
                    handle(count, curr, 3, condition3, condition1);
                    break;
            }
        } finally {
            lock.unlock();
        }
    }

    private void handle(int count, int curr, int flag, Condition await, Condition signal) {
//        lock.lock();
        try {
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
//            lock.unlock();
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
