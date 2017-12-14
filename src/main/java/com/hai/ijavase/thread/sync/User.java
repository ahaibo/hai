package com.hai.ijavase.thread.sync;

import java.util.concurrent.locks.Lock;

public class User
{
	/** 标识是否调用本类线程同步演示的同步代码块的方法，默认true */
	public static boolean isExecSyncBlockMethod = true;
	/** 用户名 */
	private String code;
	/** 操作的金额 */
	private int cash;
	/** 我的账户 */
	private MyCount myCount;
	/** 执行操作所需的锁对象 */
	private Lock lock;
	
	public User()
	{
	}
	
	User(String code, int cash)
	{
		this.code = code;
		this.cash = cash;
	}
	
	/**
	 * @param code
	 * @param cash
	 * @param myCount
	 */
	public User(String code, int cash, MyCount myCount)
	{
		this.code = code;
		this.cash = cash;
		this.myCount = myCount;
	}
	
	/**
	 * @param code
	 * @param cash
	 * @param myCount
	 * @param lock
	 */
	public User(String code, int cash, MyCount myCount, Lock lock)
	{
		this.code = code;
		this.cash = cash;
		this.myCount = myCount;
		this.lock = lock;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public void oper(int x)
	{
		if (!isExecSyncBlockMethod)
		{
			operSyncMethod(x);
		}
		else
		{
			operSyncBlock(x);
		}
	}
	
	/**
	 * 业务方法： 同步方法
	 * 
	 * @param x
	 *            添加x万元
	 */
	public synchronized void operSyncMethod(int x)
	{
		try
		{
			Thread.sleep(10L);
			this.cash += x;
			System.out.println(Thread.currentThread().getName() + ".operSyncMethod 运行结束，增加“" + x + "”，当前用户账户余额为："
					+ cash);
			Thread.sleep(10L);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 同步代码块
	 * 
	 * Java线程：线程的同步-同步块
	 * 
	 * 对于同步，除了同步方法外，还可以使用同步代码块，有时候同步代码块会带来比同步方法更好的效果。
	 * 
	 * 追其同步的根本的目的，是控制竞争资源的正确的访问，因此只要在访问竞争资源的时候保证同一时刻只能一个线程访问即可，因此Java引入了同步代码快的策略
	 * ，以提高性能。
	 * 
	 * 注意： <br/>
	 * 在使用synchronized关键字时候，
	 * 应该尽可能避免在synchronized方法或synchronized块中使用sleep或者yield方法
	 * ，因为synchronized程序块占有着对象锁，你休息那么其他的线程只能一边等着你醒来执行完了才能执行。不但严重影响效率，也不合逻辑。
	 * 同样，在同步程序块内调用yeild方法让出CPU资源也没有意义
	 * ，因为你占用着锁，其他互斥线程还是无法访问同步程序块。当然与同步程序块无关的线程可以获得更多的执行时间。
	 * 
	 * @param x
	 */
	public void operSyncBlock(int x)
	{
		try
		{
			Thread.sleep(10L);
			synchronized (this)
			{
				this.cash += x;
				System.out.println(Thread.currentThread().getName() + ".operSyncBlock 运行结束，增加“" + x + "”，当前用户账户余额为："
						+ cash);
			}
			Thread.sleep(10L);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 模拟转账业务
	 */
	public void transferAccount()
	{
		// 获取锁
		this.lock.lock();
		// 执行现金业务
		System.out.println(this.code + "正在操作 " + myCount + " 账户，操作金额为: " + this.cash + "，当前余额为: "
				+ myCount.getRemainingSum());
		myCount.setRemainingSum(myCount.getRemainingSum() + this.cash);
		System.out.println(this.code + "操作" + myCount + "账户成功，操作金额为: " + this.cash + "，当前金额为: "
				+ myCount.getRemainingSum() + "\n");
		
		// 释放锁，否则别的线程没有机会执行了
		this.lock.unlock();
	}
	
	/**
	 * @return the myCount
	 */
	public MyCount getMyCount()
	{
		return myCount;
	}
	
	/**
	 * @param myCount
	 *            the myCount to set
	 */
	public void setMyCount(MyCount myCount)
	{
		this.myCount = myCount;
	}
	
	/**
	 * @return the lock
	 */
	public Lock getLock()
	{
		return lock;
	}
	
	/**
	 * @param lock
	 *            the lock to set
	 */
	public void setLock(Lock lock)
	{
		this.lock = lock;
	}
	
	/**
	 * @return the cash
	 */
	public int getCash()
	{
		return cash;
	}
	
	/**
	 * @param cash
	 *            the cash to set
	 */
	public void setCash(int cash)
	{
		this.cash = cash;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "User [code=" + code + ", cash=" + cash + ", myCount=" + myCount + "]";
	}
}