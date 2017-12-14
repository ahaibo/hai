/**
 *
 */
package com.hai.ijavase.thread.yield;

/**
 * @author Administrator
 */
public class YieldThread extends Thread {
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread 1 execution at " + (i + 1) + " \tobj1" + i);
        }
    }
}
