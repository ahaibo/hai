/**
 *
 */
package com.hai.javase.thread.yield;

/**
 * @author Administrator
 */
public class YieldRunnable implements Runnable {

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread 2 execution at " + (i + 1) + " \tobj2" + i);
            Thread.yield();
        }
    }

}
