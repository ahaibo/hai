package com.hai.javase.thread.test.sky;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 改造后的代码
 * Created by Administrator on 2017/12/6.
 */
public class LogThread {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue(16);
        System.out.println("begin: " + (System.currentTimeMillis() / 1000));
        /**
         * 模拟处理16行日志，原需要16秒，修过代码，开4个线程让这16个对象在4秒内打完
         */
        for (int i = 0; i < 16; i++) {//此行代码不能修过
            String log = "" + (i + 1);//此行代码不能修过
            try {
                queue.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String log = queue.take();
                            parseLog(log);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    //方法内部代码不能修过
    private static void parseLog(String log) {
        System.out.println(log + " : " + (System.currentTimeMillis() / 1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
