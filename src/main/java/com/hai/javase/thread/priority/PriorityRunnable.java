/**
 * 
 */
package com.hai.javase.thread.priority;

/**
 * @author Administrator
 * 
 */
public class PriorityRunnable implements Runnable
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println("线程2第 " + (i + 1) + " 次执行");
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
