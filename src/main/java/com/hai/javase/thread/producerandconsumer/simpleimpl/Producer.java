/**
 * 
 */
package com.hai.javase.thread.producerandconsumer.simpleimpl;

/**
 * @author Administrator
 * 
 */
public class Producer extends Thread
{
	/** 生产工厂 */
	private Godown godown;
	/** 生产数量 */
	private int produceNum;
	
	public Producer()
	{
	}
	
	/**
	 * @param godown
	 * @param produceNum
	 */
	public Producer(int produceNum, Godown godown)
	{
		this.godown = godown;
		this.produceNum = produceNum;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		// 生产产品
		this.godown.produce(this.produceNum);
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
	 * @return the produceNum
	 */
	public int getProduceNum()
	{
		return produceNum;
	}
	
	/**
	 * @param produceNum
	 *            the produceNum to set
	 */
	public void setProduceNum(int produceNum)
	{
		this.produceNum = produceNum;
	}
	
}
