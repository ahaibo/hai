package com.hai.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/9/6.
 */
public class MyTimer extends TimerTask {

    private int count = 3;
    private boolean isDone;

    @Override
    public void run() {
        if (count <= 0) {
            this.isDone = true;
            System.out.println(this.getClass().getName() + ".done...");
            this.cancel();
        }
        System.out.println("mytimer...");
        this.count--;
    }


    public static void startTimer() {
        long interval = 3000;
        Timer timer = new Timer();
        MyTimer myTimer = new MyTimer();
        timer.schedule(myTimer, interval, interval);
        System.out.println("MyTimer.startTimer...");
        while (true) {
            if (myTimer.isDone) {
                System.out.println("timer canceled...");
                timer.cancel();
                timer.purge();
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        startTimer();
    }
}
