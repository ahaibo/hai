/**
 *
 */
package com.hai.ijavase.thread.yield;

/**
 * @author Administrator
 */
public class YieldTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new YieldRunnable());

        Thread thread2 = new YieldThread();

        thread1.start();
        thread2.start();
    }
}
