package com.hai.javase.thread.threadpool.ithreadpool;

/**
 * 自定义线程池之工作线程
 * 自定义线程池的最基本三要素
 * <p>
 * 1.任务接口
 * 2.任务列表
 * 3.任务工作线程
 * <p>
 * 完成了这几个基础部分,就需要有一个对工作线程的调度线程.完成以下几个功能:
 * 1.生成需要的工作线程.由于创建线程需要一定的开销,一定要注意所创建的所有线程不能超一个设定
 * 的最大值.建议最大值不要超25.
 * <p>
 * 2.动态自适应调整集合中线程数.当有太多的线程处于闲置状态时(队列中没有任务),应该按一定比例
 * 销毁闲置了一定时的线程.如果队列中任务队列积压太多而工作线程总数没有超最大线程数时应该及时
 * 创建工作线程直至达到是大值.
 * <p>
 * 3.需要一个专门的后台线程定时扫描队列中任务与正在工作的线程总数,闲置的线程总数.
 * 以上功能有不同优化方法实现,可以参考JDK的线程池实现.
 * <p>
 * Created by Administrator on 2018/1/29.
 */
public class TaskWork extends Thread {

    private TaskList taskList;

    public TaskWork(String name, TaskList taskList) {
        super(name);
        this.taskList = taskList;
    }

    @Override
    public void run() {
        if (null == taskList || taskList.getCount() == 0) {
            return;
        }

        ITask task = null;
        while (true) {
            try {
                task = taskList.getTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (null != task) {
                task.task();
            }
        }
    }
}
