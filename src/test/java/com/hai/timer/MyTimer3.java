package com.hai.timer;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/9/6.
 */
public class MyTimer3 implements Runnable {

    private ScheduledThreadPoolExecutor executor;
    private int count;
    private Date start;
    private boolean isDone;

    @Override
    public void run() {
        synchronized (this) {
            if (this.count-- <= 0) {
                this.isDone = true;
                System.out.println(this.getClass().getName() + ".done...");
            } else {
                System.out.println("mytimer.count: " + this.count);
            }
        }
    }


    public void startTimer() {
        long interval = 3000;
        int count = 3;
        this.start = new Date();
        this.count = count;

        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
        System.out.println("MyTimer.startTimer...");

        while (true) {
            if (this.isDone) {
                stopTimer();
                break;
            }

            shutdownTimer(count, interval);

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Timer finished...");
    }

    private void stopTimer() {
        if (null != executor) {
            executor.shutdownNow();
            executor = null;
        }
        System.out.println("timer canceled...");
    }

    private void shutdownTimer(int count, long interval) {
        long between = (new Date()).getTime() - this.start.getTime();
        if (between > interval * count * 1000) {
            System.out.println("shutdownTimer...");
            this.stopTimer();
        }
    }

    public static void main(String[] args) {
        new MyTimer3().startTimer();
    }
}
