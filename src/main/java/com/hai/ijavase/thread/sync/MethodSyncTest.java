package com.hai.ijavase.thread.sync;

/**
 * @author Administrator
 * 
 */
public class MethodSyncTest
{
	public static void main(String[] args)
	{
		User user = new User("张三", 1000);
		User.isExecSyncBlockMethod = true;
		
		MethodSyncRunnable methodSyncRunnable1 = new MethodSyncRunnable(user, "线程A", 20);
		MethodSyncRunnable methodSyncRunnable2 = new MethodSyncRunnable(user, "线程B", -120);
		MethodSyncRunnable methodSyncRunnable3 = new MethodSyncRunnable(user, "线程C", 200);
		MethodSyncRunnable methodSyncRunnable4 = new MethodSyncRunnable(user, "线程D", 40);
		MethodSyncRunnable methodSyncRunnable5 = new MethodSyncRunnable(user, "线程E", 80);
		MethodSyncRunnable methodSyncRunnable6 = new MethodSyncRunnable(user, "线程F", -320);
		MethodSyncRunnable methodSyncRunnable7 = new MethodSyncRunnable(user, "线程G", 25);
		MethodSyncRunnable methodSyncRunnable8 = new MethodSyncRunnable(user, "线程H", -425);
		
		Thread thread1 = new Thread(methodSyncRunnable1);
		Thread thread2 = new Thread(methodSyncRunnable2);
		Thread thread3 = new Thread(methodSyncRunnable3);
		Thread thread4 = new Thread(methodSyncRunnable4);
		Thread thread5 = new Thread(methodSyncRunnable5);
		Thread thread6 = new Thread(methodSyncRunnable6);
		Thread thread7 = new Thread(methodSyncRunnable7);
		Thread thread8 = new Thread(methodSyncRunnable8);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
	}
}
