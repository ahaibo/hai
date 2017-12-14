/**
 * 
 */
package com.hai.ijavase.thread.sync.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.hai.common.util.RandomUtil;

/**
 * @author Administrator
 * 
 */
public class SemaphoreTest
{
	public static void main(String[] args)
	{
		int permitSize = 20;
		boolean isfair = true;
		
		SemaphorePool semaphorePool = new SemaphorePool(permitSize, isfair);
		
		Thread thread1 = new SemaphoreThread("线程A", semaphorePool, RandomUtil.random(3, 15));
		Thread thread2 = new SemaphoreThread("线程B", semaphorePool, RandomUtil.random(3, 15));
		Thread thread3 = new SemaphoreThread("线程C", semaphorePool, RandomUtil.random(3, 15));
		Thread thread4 = new SemaphoreThread("线程D", semaphorePool, RandomUtil.random(3, 15));
		Thread thread5 = new SemaphoreThread("线程E", semaphorePool, RandomUtil.random(3, 15));
		Thread thread6 = new SemaphoreThread("线程F", semaphorePool, RandomUtil.random(3, 15));
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		executorService.execute(thread1);
		executorService.execute(thread2);
		executorService.execute(thread3);
		executorService.execute(thread4);
		executorService.execute(thread5);
		executorService.execute(thread6);
		
		executorService.shutdown();
	}
}
