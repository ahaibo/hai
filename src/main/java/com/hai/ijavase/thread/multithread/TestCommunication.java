package com.hai.ijavase.thread.multithread;

class PrintNum implements Runnable {
	int num;
	static final int sleep = 1000;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {// 获得锁
				notify();// 唤醒另一线程
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (num < 100) {
					num++;
					System.out.println(Thread.currentThread().getName() + ": " + num);
				} else {
					break;
				}
				try {
					wait();// 释放CPU执行权
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

/**
 * 
 * <pre>
 * 测试线程甲、乙交替打印数值 1-100
 * 
 * 线程通信：wait(),notify()/notifyAll()
 * 线程通信必须配合sychronized
 * </pre>
 * 
 * @author as
 *
 */
public class TestCommunication {

	public static void main(String[] args) {
		PrintNum p = new PrintNum();
		Thread t1 = new Thread(p, "甲");
		Thread t2 = new Thread(p, "乙");

		t1.start();
		t2.start();
	}
}
