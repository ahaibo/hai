/**
 * 
 */
package com.hai.javase.thread.daemon;

/**
 * @author Administrator
 * 
 */
public class DaemonThread extends Thread
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		for (int i = 0; i < 99999; i++)
		{
			System.out.println("Daemon thread 第 " + (i + 1) + " 次执行");
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
