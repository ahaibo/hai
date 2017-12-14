package com.hai.ijavase.thread.test.sky;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 改造后的代码
 * Created by Administrator on 2017/12/6.
 */
public class ProduceConsumer {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        String input = queue.take();
                        String output = ProduceConsumerDo.doSome(input);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("begin: " + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 10; i++) {//此行代码不能修过
            String input = i + "";//此行代码不能修过
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void test() {
        String a = "1" + "";
        String b = "1" + "";
        System.out.println(a == b);//true; 纯常量，编译器会优化
    }

    @Test
    public void test2() {
        String a = append("1", "");
        String b = append("1", "");
        System.out.println(a == b);//false; 变量，编译器不能优化
    }

    public String append(String str1, String str2) {
        return str1 + str2;
    }
}
