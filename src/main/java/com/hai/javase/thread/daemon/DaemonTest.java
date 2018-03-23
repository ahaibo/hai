package com.hai.javase.thread.daemon;

/**
 * @author Administrator
 * 
 */
public class DaemonTest
{
	public static void main(String[] args)
	{
		Thread thread1 = new NormalThread1();
		Thread thread2 = new NormalThread2();
		Thread thread3 = new Thread(new NormalRunnable1());
		Thread thread4 = new Thread(new NormalRunnable2());
		
		Thread daemonThread = new DaemonThread();
		// 将线程daemonThread设置为守护线程
		daemonThread.setDaemon(true);
		
		// 实际上：JRE判断程序是否执行结束的标准是所有的前台线程执行完毕了，而不管后台线程【守护线程】的状态，因此，在使用后台线程的时候一定要注意这个问题。
		daemonThread.start();
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}
}
