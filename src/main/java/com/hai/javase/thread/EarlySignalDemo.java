package com.hai.javase.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程过早唤醒
 * <p>
 * 更正的办法就是把remove方法中对list是否为空的判断改为while循环就可以了。
 * <p>
 * 小结
 * 当使用条件等待时，往往需要对等待条件进行循环测试，避免过早的通知。
 */
public class EarlySignalDemo {

    //元素列表
    private List<String> list;
    //日期格式器
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");
    //计数器
    private AtomicLong number = new AtomicLong();

    public EarlySignalDemo() {
        list = new ArrayList<>();
    }

    //对list执行删除的元素
    public void remove() throws InterruptedException {
        synchronized (list) {
            /*while*/ //当使用条件等待时，往往需要对等待条件进行循环测试，避免过早的通知。
            if (list.isEmpty()) {
                //只要list为空，那么调用此方法的线程必须等待
                list.wait();
            }
            //如果执行到这里，说明list已经不为空了
            //这样执行元素的删除操作才不会出错
            String item = list.remove(0);
            System.out.println(Thread.currentThread().getName() + ": remove element " + item + "! "
                    + format.format(new Date()));
        }
    }

    //对list执行添加操作
    public void add() {
        synchronized (list) {
            //添加元素不要进行判断
            list.add("" + number.incrementAndGet());
            System.out.println(Thread.currentThread().getName() + ": add item " + number.get()
                    + " " + format.format(new Date()));
            list.notifyAll();
        }
    }

    static class AddThread implements Runnable {
        private EarlySignalDemo es;

        public AddThread(EarlySignalDemo es) {
            this.es = es;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(600);
                es.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class RemoveThread implements Runnable {
        private EarlySignalDemo es;

        public RemoveThread(EarlySignalDemo es) {
            this.es = es;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                es.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EarlySignalDemo es = new EarlySignalDemo();
        for (int i = 0; i < 3; i++) {
            new Thread(new RemoveThread(es), "RemoveThread" + i).start();
        }
        new Thread(new AddThread(es), "AddThread").start();
    }
}