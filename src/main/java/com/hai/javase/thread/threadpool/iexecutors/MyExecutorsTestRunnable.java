package com.hai.javase.thread.threadpool.iexecutors;

public class MyExecutorsTestRunnable implements Runnable {
    private String threadName;
    private boolean depleteCalc;
    private int calcSize = 1000000;

    public MyExecutorsTestRunnable() {
    }

    public MyExecutorsTestRunnable(boolean depleteCalc) {
        this.depleteCalc = depleteCalc;
    }

    public MyExecutorsTestRunnable(String threadName) {
        this.threadName = threadName;
    }

    public MyExecutorsTestRunnable(String threadName, boolean depleteCalc) {
        this.threadName = threadName;
        this.depleteCalc = depleteCalc;
    }

    public MyExecutorsTestRunnable(String threadName, boolean depleteCalc, int calcSize) {
        this(threadName, depleteCalc);
        this.calcSize = calcSize;
    }

    /*
         * (non-Javadoc)
         *
         * @see java.lang.Runnable#run()
         */
    @Override
    public void run() {
//        System.out.println("\n" + Thread.currentThread().getName() + "." + this.threadName + "正在执行");
        if (depleteCalc) {
            depleteCPUCalc();
        }
    }

    public void depleteCPUCalc() {
//        System.out.println(this.getClass().getName() + ".depleteCPUCalc main...");
//        long main = System.nanoTime();
        for (int i = 0; i < calcSize; i++) {
//            System.out.println("Math.hypot(Math.pow(924526789, " + i + "), Math.cos(" + i + "))");
            Math.hypot(Math.pow(924526789, i), Math.cos(i));
        }

//        long end = System.nanoTime();
//        System.out.println(this.getClass().getName() + ".depleteCPUCalc end...");
//        System.out.println("used time: " + (end - main));
//        System.out.println();
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

}