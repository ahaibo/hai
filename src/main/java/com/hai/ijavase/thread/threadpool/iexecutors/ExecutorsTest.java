/**
 * 
 */
package com.hai.ijavase.thread.threadpool.iexecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * 
 */
public class ExecutorsTest
{
	public static void main(String[] args)
	{
		ExecutorService executorService = null;
		// 创建一个可重用固定线程数的线程池
		Executors.newFixedThreadPool(5);
		
		// 创建一个使用单个 worker 线程的 AsyncDemoServletExecutor，以无界队列方式来运行该线程。
		Executors.newSingleThreadExecutor();
		
		// 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		executorService = Executors.newCachedThreadPool();
		
		// 将实现了Runnable接口的线程类放入池中执行
		executorService.execute(new MyExecutorsTestRunnable("线程A"));
		executorService.execute(new MyExecutorsTestRunnable("线程B"));
		executorService.execute(new MyExecutorsTestRunnable("线程C"));
		executorService.execute(new MyExecutorsTestRunnable("线程D"));
		executorService.execute(new MyExecutorsTestRunnable("线程E"));
		executorService.execute(new MyExecutorsTestRunnable("线程F"));
		executorService.execute(new MyExecutorsTestRunnable("线程G"));
		executorService.execute(new MyExecutorsTestRunnable("线程H"));
		executorService.execute(new MyExecutorsTestRunnable("线程I"));
		executorService.execute(new MyExecutorsTestRunnable("线程J"));
		executorService.execute(new MyExecutorsTestRunnable("线程K"));
		
		// 关闭线程池
		executorService.shutdown();
	}
}




