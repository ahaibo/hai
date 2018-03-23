package com.hai.javase.thread.sync.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 模拟项目的开发，只有当每个模块都完成后，项目才完成 每个模块的用时不同
 *
 * @author Administrator
 */
class Module implements Runnable {
    private CountDownLatch latch;
    private String moduleName;
    private int time;// 用时

    public Module(CountDownLatch latch, String moduleName, int time) {
        this.latch = latch;
        this.moduleName = moduleName;
        this.time = time;
    }

    @Override
    public void run() {
        try {
            work();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void work() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(time);
        System.out.println(moduleName + " 完成，\t耗时:" + time);
    }
}

class Controller implements Runnable {
    private CountDownLatch latch;

    public Controller(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("\n所有模块都完成，任务完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

public class Project {
    static final int SIZE = 20;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(SIZE);
        Random r = new Random();
        ExecutorService exec = Executors.newCachedThreadPool();
        Controller controller = new Controller(latch);
        exec.execute(controller);

        for (int i = 0; i < SIZE; i++) {
            exec.execute(new Module(latch, "模块" + (i + 1), r.nextInt(2000)));
        }

        exec.shutdown();

    }

}