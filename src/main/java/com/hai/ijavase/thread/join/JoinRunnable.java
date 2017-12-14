/**
 * 
 */
package com.hai.ijavase.thread.join;

/**
 * @author Administrator
 * 
 */
public class JoinRunnable implements Runnable
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		for (int i = 0; i < 20; i++)
		{
			System.out.println("子线程：" + Thread.currentThread().getName() + " 第 " + (i + 1) + " 次执行。");
		}
	}
	
}
