/**
 *
 */
package com.hai.ijavase.thread.threadpool;

/**
 * @author Administrator
 */
public class ChildrenWorker extends Worker {

    /**
     * @param threadName
     * @param manager
     */
    public ChildrenWorker(String threadName, Manager manager) {
        super(threadName, manager);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hai.hai.ijavase.thread.WorkerExample#init()
     */
    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.hai.hai.ijavase.thread.WorkerExample#process()
     */
    @Override
    public void process() {
        // TODO Auto-generated method stub

    }

}
