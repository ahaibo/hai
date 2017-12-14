/**
 * 
 */
package com.hai.ijavase.thread.daemon;

/**
 * @author Administrator
 * 
 */
public class NormalRunnable2 implements Runnable
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
			System.out.println("NormalRunnable2 thread 第 " + (i + 1) + " 次执行");
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
