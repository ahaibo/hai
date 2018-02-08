package com.hai.ijavase.thread.threadpool.ithreadpool;

import java.util.LinkedList;

/**
 * 自定义线程池之任务列表(如本例的 FIFO 列表 LinkedList)
 * Created by Administrator on 2018/1/29.
 */
public class TaskList {

    private LinkedList<ITask> list = new LinkedList();

    public synchronized void add(ITask task) {
        this.list.add(task);
    }

    public synchronized ITask getTask() throws InterruptedException {
        if (this.list.size() <= 0) {
            this.wait();
        }
        return this.list.poll();
    }

    public synchronized int getCount() {
        return this.list.size();
    }

    public synchronized void clear() {
        this.list.clear();
    }
}
