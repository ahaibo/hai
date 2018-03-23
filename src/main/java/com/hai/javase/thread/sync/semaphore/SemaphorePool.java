/**
 *
 */
package com.hai.javase.thread.sync.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 */
public class SemaphorePool {
    /**
     * 池相关的信号量
     */
    private Semaphore semaphore;
    /**
     * 信号量大小
     */
    private int permitSize;
    /**
     * 是否以公平的方式获取信号量
     */
    private boolean isfair;
    /**
     * 默认信号量大小
     */
    private static final int DEFAULT_PERMIT_SIZE = 10;

    public SemaphorePool() {
        this.semaphore = new Semaphore(DEFAULT_PERMIT_SIZE);
    }

    /**
     * @param permitSize
     */
    public SemaphorePool(int permitSize) {
        this.permitSize = permitSize;
        this.semaphore = new Semaphore(permitSize);
    }

    /**
     * @param permitSize
     * @param isfair
     */
    public SemaphorePool(int permitSize, boolean isfair) {
        this.permitSize = permitSize;
        this.isfair = isfair;
        this.semaphore = new Semaphore(permitSize, isfair);
    }

    /**
     * @return the semaphore
     */
    public Semaphore getSemaphore() {
        return semaphore;
    }

    /**
     * @param semaphore the semaphore to set
     */
    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    /**
     * @return the permitSize
     */
    public int getPermitSize() {
        return permitSize;
    }

    /**
     * @param permitSize the permitSize to set
     */
    public void setPermitSize(int permitSize) {
        this.permitSize = permitSize;
    }

    /**
     * @return the isfair
     */
    public boolean isIsfair() {
        return isfair;
    }

    /**
     * @param isfair the isfair to set
     */
    public void setIsfair(boolean isfair) {
        this.isfair = isfair;
    }

}
