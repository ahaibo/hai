package com.hai.ijavase.thread.threadpool;

public abstract class Worker implements Runnable {

    private Manager mManager = null;

    private Thread mThread = null;

    private boolean isRunning;

    public Worker() {
    }

    public Worker(String threadName, Manager manager) {
        mManager = manager;
        mThread = new Thread(this, threadName); // 每次都产生一个新的线程对象
        init();
        mThread.start(); // 启动线程
    }

    public abstract void init();

    public void run() {
        while (true) {
            waitForStart(); // 设置线程为等待状态
            Worker worker = mManager.getIdleWorker();
            process();
            setRunning(false);
            mManager.notifyFree(worker);
        }
    }

    public abstract void process();

    public void start() {
        setRunning(true);
        mManager.getIdleWorker();
        notifyToStart();
    }

    public synchronized void waitForStart() {
        try {
            wait();
        } catch (InterruptedException ex) {
        }
    }

    public synchronized void notifyToStart() {
        notify();
    }

    /**
     * @return the isRunning
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * @param isRunning the isRunning to set
     */
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}