package com.hai.javase.thread.threadpool;

import java.util.LinkedList;

public abstract class Manager {

    private String mThreadPoolName = null; // 线程池名称

    private int mThreadPoolMaxSize = 1; // 线程池最大线程数

    private LinkedList<Worker> workers = new LinkedList<Worker>(); // 工作线程的集合

    public Manager() {
    }

    public Manager(String name, int poolMaxSize) { // 设置线程池的名称和最大线程数
        mThreadPoolName = name;
        createWorker(name, poolMaxSize); // 创建最大数据的线程
        mThreadPoolMaxSize = poolMaxSize;
    }

    private void createWorker(String threadName, int poolMaxSize) {
        for (int i = 0; i < poolMaxSize; i++) {
            Worker worker = new ChildrenWorker(threadName, this);
            workers.addLast(worker);
        }
    }

    public synchronized Worker getIdleWorker() {
        return (Worker) workers.removeFirst(); // 返回一个移除第一个线程对象的线程集合
    }

    public synchronized void notifyFree(Worker worker) {
        if (workers.size() < mThreadPoolMaxSize) {
            workers.addLast(worker); // 如果线程集合中的线程对象数小于线程池最大线程数，则添加一个新到线程互线程集合中去
        } else {
            worker = null; // 否则，设置线程集合为null
        }
    }

    public int getThreadPoolMaxSize() { // 取得线程池中最大的线程数
        return mThreadPoolMaxSize;
    }

    public void setThreadPoolMaxSize(int threadPoolMaxSize) { // 设置线程池中最大线程数
        this.mThreadPoolMaxSize = threadPoolMaxSize;
    }

    /**
     * @return the mThreadPoolName
     */
    public String getmThreadPoolName() {
        return mThreadPoolName;
    }

    /**
     * @param mThreadPoolName the mThreadPoolName to set
     */
    public void setmThreadPoolName(String mThreadPoolName) {
        this.mThreadPoolName = mThreadPoolName;
    }

    /**
     * @return the mThreadPoolMaxSize
     */
    public int getmThreadPoolMaxSize() {
        return mThreadPoolMaxSize;
    }

    /**
     * @param mThreadPoolMaxSize the mThreadPoolMaxSize to set
     */
    public void setmThreadPoolMaxSize(int mThreadPoolMaxSize) {
        this.mThreadPoolMaxSize = mThreadPoolMaxSize;
    }

    /**
     * @return the workers
     */
    public LinkedList<Worker> getWorkers() {
        return workers;
    }

    /**
     * @param workers the workers to set
     */
    public void setWorkers(LinkedList<Worker> workers) {
        this.workers = workers;
    }

}