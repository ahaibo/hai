/**
 *
 */
package com.hai.javase.thread.sync.semaphore;

/**
 * @author Administrator
 */
public class SemaphoreThread extends Thread {
    private String threadName;
    private SemaphorePool semaphorePool;
    private int semaphoreSize;

    public SemaphoreThread() {
    }

    /**
     * @param threadName
     * @param semaphorePool
     * @param semaphoreSize
     */
    public SemaphoreThread(String threadName, SemaphorePool semaphorePool, int semaphoreSize) {
        this.threadName = threadName;
        this.semaphorePool = semaphorePool;
        this.semaphoreSize = semaphoreSize;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        try {
            // 从此信号量获取给定数目的许可
            this.semaphorePool.getSemaphore().acquire(semaphoreSize);
            System.out.println(threadName + "成功获取了" + semaphoreSize + "个许可！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放给定数目的许可，将其返回到信号量。
            this.semaphorePool.getSemaphore().release(semaphoreSize);
            System.out.println(threadName + "释放了" + semaphoreSize + "个许可！");
        }
    }

    /**
     * @return the threadName
     */
    public String getThreadName() {
        return threadName;
    }

    /**
     * @param threadName the threadName to set
     */
    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    /**
     * @return the semaphorePool
     */
    public SemaphorePool getSemaphorePool() {
        return semaphorePool;
    }

    /**
     * @param semaphorePool the semaphorePool to set
     */
    public void setSemaphorePool(SemaphorePool semaphorePool) {
        this.semaphorePool = semaphorePool;
    }

    /**
     * @return the semaphoreSize
     */
    public int getSemaphoreSize() {
        return semaphoreSize;
    }

    /**
     * @param semaphoreSize the semaphoreSize to set
     */
    public void setSemaphoreSize(int semaphoreSize) {
        this.semaphoreSize = semaphoreSize;
    }

}
