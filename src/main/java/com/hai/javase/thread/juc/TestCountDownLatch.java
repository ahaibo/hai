package com.hai.javase.thread.juc;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch ：闭锁，在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才继续执行
 * Created by Administrator on 2018/3/24.
 */
public class TestCountDownLatch {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(50);
        CountDownLatchDemo instance = new TestCountDownLatch().new CountDownLatchDemo(latch);
        for (int i = 1; i <= 50; i++) {
            String threadName = "T" + (i < 10 ? "0" + i : "" + i);
            new Thread(instance, threadName).start();
        }
        try {
            latch.await();
            System.out.println("finished...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class CountDownLatchDemo implements Runnable {

        private CountDownLatch latch;

        public CountDownLatchDemo(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(10000));
                System.out.println(Thread.currentThread().getName() + " sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.latch.countDown();
                System.out.println(Thread.currentThread().getName() + " countdown latch...");
            }

        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(new Random().nextInt(10000));
        }
    }
}

