package com.hai.desinpattern.other.producerconsumer;

public class ProducerConsumerTest {
    public static void main(String[] args) {
        CubbyHole c = new CubbyHole();
        Producer p1 = new Producer(c, 1);
        Consumer c1 = new Consumer(c, 1);
        p1.start();
        c1.start();
    }

    static class CubbyHole {
        private int contents;
        private boolean available = false;

        public synchronized int get() {
            while (!available) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            available = false;
            notifyAll();
            return contents;
        }

        public synchronized void put(int value) {
            while (available) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            contents = value;
            available = true;
            notifyAll();
        }
    }

    static class Consumer extends Thread {
        private CubbyHole cubbyhole;
        private int number;

        public Consumer(CubbyHole c, int number) {
            cubbyhole = c;
            this.number = number;
        }

        public void run() {
            int value = 0;
            for (int i = 0; i < 10; i++) {
                value = cubbyhole.get();
                System.out.println("消费者 #" + this.number + " got: " + value);
            }
        }
    }

    static class Producer extends Thread {
        private CubbyHole cubbyhole;
        private int number;

        public Producer(CubbyHole c, int number) {
            cubbyhole = c;
            this.number = number;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                cubbyhole.put(i);
                System.out.println("生产者 #" + this.number + " put: " + i);
                try {
                    sleep((int) (Math.random() * 100));
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

