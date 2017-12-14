package com.hai.ijavase.thread.sync.lock;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.hai.common.util.RandomUtil;
import com.hai.ijavase.thread.sync.MyCount;
import com.hai.ijavase.thread.sync.User;

/**
 * @author Administrator
 * 
 */
public class ReadWriteLockTest
{
	public static void main(String[] args)
	{
		// 创建一个线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		// 创建并发访问的账户
		int remainingSum = 10000;
		MyCount myCount = new MyCount(UUID.randomUUID().toString(), remainingSum);
		// 创建一个锁对象
		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		
		User user1 = new User("张三", 200, myCount);
		User user2 = new User("李四", -1100, myCount);
		User user3 = new User("王五", 800, myCount);
		User user4 = new User("赵二", -6600, myCount);
		User user5 = new User("钱一", 5000, myCount);
		
		// 在线程池中执行各个用户的操作
		executorService.execute(new UserReadWriteLockRunnable(user1, readWriteLock, RandomUtil.randomBoolean()));
		executorService.execute(new UserReadWriteLockRunnable(user2, readWriteLock, RandomUtil.randomBoolean()));
		executorService.execute(new UserReadWriteLockRunnable(user3, readWriteLock, RandomUtil.randomBoolean()));
		executorService.execute(new UserReadWriteLockRunnable(user4, readWriteLock, RandomUtil.randomBoolean()));
		executorService.execute(new UserReadWriteLockRunnable(user5, readWriteLock, RandomUtil.randomBoolean()));
		
		// 关闭线程池
		executorService.shutdown();
	}
}
