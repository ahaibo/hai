package com.hai.javase.thread.juc;

import java.util.Random;

/**
 * 模拟 CAS 算法
 * Created by Administrator on 2018/3/23.
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        CompareAndSwap compareAndSwap = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = compareAndSwap.get();
                    boolean result = compareAndSwap.compareAndSet(expectedValue, new Random().nextInt());
                    System.out.println(result);
                }
            }).start();
        }
        System.out.println();
    }
}

class CompareAndSwap {
    private int value;

    //获取内存值
    public synchronized int get() {
        return this.value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = this.value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }

    //设值
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}