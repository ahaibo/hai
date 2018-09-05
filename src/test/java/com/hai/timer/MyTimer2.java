package com.hai.timer;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/6.
 */
public class MyTimer2 implements Runnable {

    private int count = 3;
    private boolean isDone;

    @Override
    public void run() {
        synchronized (MyTimer2.class) {
            if (this.count-- <= 0) {
                this.isDone = true;
                System.out.println(this.getClass().getName() + ".done...");
            }else {
                System.out.println("mytimer...");
            }
        }
    }


    public static void startTimer() {
        long interval = 3000;
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        MyTimer2 myTimer = new MyTimer2();
        executor.scheduleAtFixedRate(myTimer, interval, interval, TimeUnit.MILLISECONDS);
        System.out.println("MyTimer.startTimer...");

        while (true) {
            if (myTimer.isDone) {
                System.out.println("timer canceled...");
                if (null != executor) {
                    executor.shutdownNow();
                    executor = null;
                    break;
                }
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Timer finished...");
    }

    public static void main(String[] args) {
        startTimer();
    }
}
