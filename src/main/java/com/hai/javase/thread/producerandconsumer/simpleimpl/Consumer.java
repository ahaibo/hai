/**
 * 
 */
package com.hai.javase.thread.producerandconsumer.simpleimpl;

/**
 * @author Administrator
 * 
 */
public class Consumer extends Thread
{
	/** 生产工厂 */
	private Godown godown;
	/** 消费数量 */
	private int consumeNum;
	
	public Consumer()
	{
	}
	
	/**
	 * @param godown
	 * @param consumeNum
	 */
	public Consumer(int consumeNum, Godown godown)
	{
		this.godown = godown;
		this.consumeNum = consumeNum;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		this.godown.consume(this.consumeNum);
	}
	
	/**
	 * @return the godown
	 */
	public Godown getGodown()
	{
		return godown;
	}
	
	/**
	 * @param godown
	 *            the godown to set
	 */
	public void setGodown(Godown godown)
	{
		this.godown = godown;
	}
	
	/**
	 * @return the consumeNum
	 */
	public int getConsumeNum()
	{
		return consumeNum;
	}
	
	/**
	 * @param consumeNum
	 *            the consumeNum to set
	 */
	public void setConsumeNum(int consumeNum)
	{
		this.consumeNum = consumeNum;
	}
	
}
