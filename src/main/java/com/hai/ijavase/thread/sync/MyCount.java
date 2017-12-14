/**
 * 
 */
package com.hai.ijavase.thread.sync;

/**
 * @author Administrator
 * 
 */
public class MyCount
{
	/** 账号 */
	private String oid;
	/** 账户余额 */
	private int remainingSum;
	
	public MyCount()
	{
	}
	
	/**
	 * @param oid
	 * @param remainingSum
	 */
	public MyCount(String oid, int remainingSum)
	{
		this.oid = oid;
		this.remainingSum = remainingSum;
	}
	
	/**
	 * @return the oid
	 */
	public String getOid()
	{
		return oid;
	}
	
	/**
	 * @param oid
	 *            the oid to set
	 */
	public void setOid(String oid)
	{
		this.oid = oid;
	}
	
	/**
	 * @return the remainingSum
	 */
	public int getRemainingSum()
	{
		return remainingSum;
	}
	
	/**
	 * @param remainingSum
	 *            the remainingSum to set
	 */
	public void setRemainingSum(int remainingSum)
	{
		this.remainingSum = remainingSum;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MyCount [oid=" + oid + ", remainingSum=" + remainingSum + "]";
	}
	
}
