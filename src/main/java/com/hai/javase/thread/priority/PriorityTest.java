/**
 * 
 */
package com.hai.javase.thread.priority;

/**
 * @author Administrator
 * 
 */
public class PriorityTest
{
	public static void main(String[] args)
	{
		PriorityThread myThread = new PriorityThread();
		
		PriorityRunnable myRunnable = new PriorityRunnable();
		Thread myRunnableThread = new Thread(myRunnable);
		
		myThread.setPriority(10);
		myRunnableThread.setPriority(1);
		
		myThread.start();
		myRunnableThread.start();
	}
}
