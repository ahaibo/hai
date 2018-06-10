package com.hai.spring.pool.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 线程池 配置
 */
@Component
public class ThreadsPoolConfig {

    /**
     * 是否开启自定义线程池
     */
    @Value("${handler.threads.custom}")
    private boolean custom;
    /**
     * 核心线程数
     */
    @Value("${handler.threads.corePoolSize}")
    private int corePoolSize;
    /**
     * 线程池最大线程数
     */
    @Value("${handler.threads.maximumPoolSize}")
    private int maximumPoolSize;
    /**
     * 空闲线程存活时间（对核心线程无效）
     */
    @Value("${handler.threads.keepAliveTime}")
    private long keepAliveTime;
    /**
     * 任务队列大小，0时为无界队列
     */
    @Value("${handler.threads.workQueue}")
    private int workQueue;

    public boolean isCustom() {
        return custom;
    }

    public void setCustom(boolean custom) {
        this.custom = custom;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(int workQueue) {
        this.workQueue = workQueue;
    }

}