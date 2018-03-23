/**
 *
 */
package com.hai.javase.thread.deadlock;

/**
 * @author Administrator
 */
public class DeadlockRunnable implements Runnable {
    private DeadlockRisk deadlockRisk;
    private int valueA, valueB;

    /**
     * @param deadlockRisk
     * @param valueA
     * @param valueB
     */
    public DeadlockRunnable(DeadlockRisk deadlockRisk, int valueA, int valueB) {
        this.deadlockRisk = deadlockRisk;
        this.valueA = valueA;
        this.valueB = valueB;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        this.deadlockRisk.read();
        this.deadlockRisk.write(valueA, valueB);
    }

    /**
     * @return the deadlockRisk
     */
    public DeadlockRisk getDeadlockRisk() {
        return deadlockRisk;
    }

    /**
     * @param deadlockRisk the deadlockRisk to set
     */
    public void setDeadlockRisk(DeadlockRisk deadlockRisk) {
        this.deadlockRisk = deadlockRisk;
    }

    /**
     * @return the valueA
     */
    public int getValueA() {
        return valueA;
    }

    /**
     * @param valueA the valueA to set
     */
    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    /**
     * @return the valueB
     */
    public int getValueB() {
        return valueB;
    }

    /**
     * @param valueB the valueB to set
     */
    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

}
