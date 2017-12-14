/**
 * 
 */
package com.hai.ijavase.thread.sync;

/**
 * @author Administrator
 * 
 */
public class MethodSyncRunnable implements Runnable
{
	
	private User user;
	private String threadName;
	private int opraCash;
	
	/**
	 * 
	 */
	public MethodSyncRunnable()
	{
	}
	
	/**
	 * @param user
	 * @param opraCash
	 */
	public MethodSyncRunnable(User user, int opraCash)
	{
		this.user = user;
		this.opraCash = opraCash;
	}
	
	/**
	 * @param user
	 * @param threadName
	 * @param opraCash
	 */
	public MethodSyncRunnable(User user, String threadName, int opraCash)
	{
		this.user = user;
		this.threadName = threadName;
		this.opraCash = opraCash;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		Thread.currentThread().setName(this.threadName);
		this.user.oper(this.opraCash);
	}
	
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}
	
	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}
	
	/**
	 * @return the opraCash
	 */
	public int getOpraCash()
	{
		return opraCash;
	}
	
	/**
	 * @param opraCash
	 *            the opraCash to set
	 */
	public void setOpraCash(int opraCash)
	{
		this.opraCash = opraCash;
	}
	
	/**
	 * @return the threadName
	 */
	public String getThreadName()
	{
		return threadName;
	}
	
	/**
	 * @param threadName
	 *            the threadName to set
	 */
	public void setThreadName(String threadName)
	{
		this.threadName = threadName;
	}
	
}
