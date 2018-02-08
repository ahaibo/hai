package com.hai.ijavase.thread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/***
 * <pre>
 * 丢失信号、唤醒丢失
 * WaitThread因为丢失了来自NotifyThread的通知而一直陷入等待中。
 * 当然，这里仅仅是演示了这种情况，在实际的例子中，执行等待的线程都需要一个等待条件，为了避免出现丢失的信号，仍然需要对条件变量进行while循环的判断。
 *
 * 关于等待通知机制的补充
 *
 * 每当在等待一个条件时，一定要确保在条件变量变为真的时候才发出唤醒的通知
 * 在调用wait/notify/notifyAll方法时，必须首先获得锁
 * 每次调用完wait方法，获得锁就会自动释放
 * 调用notify时，JVM从等待队列中的一个线程进行唤醒，调用notifyAll时，将等待队列中所有线程都唤醒
 * 只有同时满足两个条件时才能使用notify：
 * 一是所有等待线程的类型都相同，这就是说，等待队列只与一个条件变量相关，并且所有的线程在唤醒后执行的都是相同的操作；
 * 二是单进单出，也就是说在条件变量的每个通知，要求只能最多唤醒一个线程
 * </pre>
 */
public class MissedNotifyDemo {

    //持有的锁
    private static Object lock = new Object();
    //日期格式器
    private static final DateFormat format = new SimpleDateFormat("HH:mm:ss");

    //等待线程执行的方法
    public void waitMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": enter waitMethod at " + format.format(new Date()));
        synchronized (lock) {
            //调用wait方法执行等待
            System.out.println(Thread.currentThread().getName() + ": start invoke wait method at " + format.format(new Date()));
            lock.wait();
            System.out.println(Thread.currentThread().getName() + ": end invoke wait method at " + format.format(new Date()));
        }
        System.out.println(Thread.currentThread().getName() + ": exit waitMethod at " + format.format(new Date()));
    }

    //通知线程执行的方法
    public void notifyMethod() {
        System.out.println(Thread.currentThread().getName() + ": exit notifyMethod at " + format.format(new Date()));
        synchronized (lock) {
            //调用通知的方法
            System.out.println(Thread.currentThread().getName() + ": start invoke notify method at " + format.format(new Date()));
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + ": end invoke notify method at " + format.format(new Date()));
        }
        System.out.println(Thread.currentThread().getName() + ": exit notifyMethod at " + format.format(new Date()));
    }

    static class WaitThread implements Runnable {
        private MissedNotifyDemo missedNotifyDemo;

        public WaitThread(MissedNotifyDemo missedNotifyDemo) {
            this.missedNotifyDemo = missedNotifyDemo;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                missedNotifyDemo.waitMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class NotifyThread implements Runnable {

        private MissedNotifyDemo missedNotifyDemo;

        public NotifyThread(MissedNotifyDemo missedNotifyDemo) {
            this.missedNotifyDemo = missedNotifyDemo;
        }

        @Override
        public void run() {
            try {
                //休眠的时间必须要小于等待线程的休眠时间
                TimeUnit.MILLISECONDS.sleep(500);
                missedNotifyDemo.notifyMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MissedNotifyDemo missedNotifyDemo = new MissedNotifyDemo();
        new Thread(new WaitThread(missedNotifyDemo), "WaitThread").start();
        new Thread(new NotifyThread(missedNotifyDemo), "NotifyThread").start();
    }
}