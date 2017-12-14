/**
 * 
 */
package com.hai.ijavase.thread.priority;

/**
 * @author Administrator
 * 
 */
public class PriorityThread extends Thread
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			System.out.println("线程1第 " + (i + 1) + " 次执行");
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
