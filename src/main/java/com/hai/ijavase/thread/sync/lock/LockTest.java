package com.hai.ijavase.thread.sync.lock;

import com.hai.ijavase.thread.sync.MyCount;
import com.hai.ijavase.thread.sync.User;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Administrator
 * 
 */
public class LockTest
{
	public static void main(String[] args)
	{
		// 创建一个线程池
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		// 创建并发访问的账户
		int remainingSum = 10000;
		MyCount myCount = new MyCount(UUID.randomUUID().toString(), remainingSum);
		// 创建一个锁对象
		Lock lock = new ReentrantLock();
		
		User user1 = new User("张三", 200, myCount, lock);
		User user2 = new User("李四", -1100, myCount, lock);
		User user3 = new User("王五", 800, myCount, lock);
		User user4 = new User("赵二", -6600, myCount, lock);
		User user5 = new User("钱一", 5000, myCount, lock);
		
		// 在线程池中执行各个用户的操作
		executorService.execute(new UserLockRunnable(user1));
		executorService.execute(new UserLockRunnable(user2));
		executorService.execute(new UserLockRunnable(user3));
		executorService.execute(new UserLockRunnable(user4));
		executorService.execute(new UserLockRunnable(user5));
		
		// 关闭线程池
		executorService.shutdown();
	}
}
