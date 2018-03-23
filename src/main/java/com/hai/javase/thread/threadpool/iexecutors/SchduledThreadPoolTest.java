package com.hai.javase.thread.threadpool.iexecutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchduledThreadPoolTest
{
	public static void main(String[] args)
	{
		// 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
		ScheduledExecutorService scheduledExecutorService = null;
		Executors.newScheduledThreadPool(2);
		
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		
		Thread thread1 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService1"));
		Thread thread2 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService2"));
		Thread thread3 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService3"));
		Thread thread4 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService4"));
		Thread thread5 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService5"));
		Thread thread6 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService6"));
		Thread thread7 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService7"));
		Thread thread8 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService8"));
		Thread thread9 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService9"));
		Thread thread10 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService10"));
		Thread thread11 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService11"));
		Thread thread12 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService12"));
		Thread thread13 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService13"));
		Thread thread14 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService14"));
		Thread thread15 = new Thread(new MyExecutorsTestRunnable("ScheduledExecutorService15"));
		
		// 将线程放入池中进行执行
		scheduledExecutorService.execute(thread1);
		scheduledExecutorService.execute(thread2);
		scheduledExecutorService.execute(thread3);
		scheduledExecutorService.execute(thread4);
		scheduledExecutorService.execute(thread5);
		scheduledExecutorService.execute(thread6);
		scheduledExecutorService.execute(thread7);
		scheduledExecutorService.execute(thread8);
		
		// 使用延迟执行风格的方法
		scheduledExecutorService.schedule(thread9, 100, TimeUnit.MILLISECONDS);
		scheduledExecutorService.schedule(thread10, 100, TimeUnit.MILLISECONDS);
		scheduledExecutorService.schedule(thread11, 100, TimeUnit.MILLISECONDS);
		scheduledExecutorService.schedule(thread12, 100, TimeUnit.MILLISECONDS);
		scheduledExecutorService.schedule(thread13, 100, TimeUnit.MILLISECONDS);
		scheduledExecutorService.schedule(thread14, 100, TimeUnit.MILLISECONDS);
		scheduledExecutorService.schedule(thread15, 100, TimeUnit.MILLISECONDS);
		
		scheduledExecutorService.shutdown();
	}
}