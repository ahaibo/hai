/**
 * 
 */
package com.hai.ijavase.thread.deadlock;

/**
 * @author Administrator
 * 
 */
public class DeadlockThreadDemo
{
	public static void main(String[] args)
	{
		DeadlockRisk deadlockRisk = new DeadlockRisk();
		
		DeadlockRunnable deadlockRunnable = new DeadlockRunnable(deadlockRisk, 10, 20);

		Thread thread1 = new Thread(deadlockRunnable);

		thread1.start();
	}
}
