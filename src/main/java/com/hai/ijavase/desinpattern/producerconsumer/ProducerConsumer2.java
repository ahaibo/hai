package com.hai.ijavase.desinpattern.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
public class ProducerConsumer2 {

    public static void main(String[] args) {
        Pool pool = new Pool();

        for (int i = 1; i <= 3; i++) {
            Thread producer = new Producer(pool);
            producer.setName("Producer" + i);
            producer.start();
        }
        for (int i = 1; i <= 2; i++) {
            Thread consumer = new Consumer(pool);
            consumer.setName("Consumer" + i);
            consumer.start();
        }
    }

    static class Producer extends Thread {
        static int num;
        private Pool pool;

        public Producer(Pool pool) {
            this.pool = pool;
        }

        public void run() {
            while (true) {
                pool.add(num++);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Pool pool;

        public Consumer(Pool pool) {
            this.pool = pool;
        }

        public void run() {
            while (true) {
                pool.remove();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Pool {
        List<Integer> list = new ArrayList<>();
        int MAX = 100;

        public void add(int num) {
            synchronized (this) {
                while (list.size() >= MAX) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(num);
                System.out.println(Thread.currentThread().getName() + " add: " + num + ";\t\tlist.size: " + list.size());
                this.notifyAll();
            }
        }

        public Object remove() {
            synchronized (this) {
                while (list.size() == 0) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + " remove: " + obj + ";\tlist.size: " + list.size());
                this.notifyAll();
                return obj;
            }
        }
    }

}
