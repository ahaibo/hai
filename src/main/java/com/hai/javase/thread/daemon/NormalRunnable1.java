/**
 * 
 */
package com.hai.javase.thread.daemon;

/**
 * @author Administrator
 * 
 */
public class NormalRunnable1 implements Runnable
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		for (int i = 0; i < 5; i++)
		{
			System.out.println("NormalRunnable1 thread 第 " + (i + 1) + " 次执行");
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
