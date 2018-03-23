/**
 * 
 */
package com.hai.javase.thread.producerandconsumer.simpleimpl;

/**
 * @author Administrator
 * 
 */
public class Godown
{
	/** 仓库允许的最大仓储数 */
	public static final int GODOWN_MAX_SIZE = 100;
	/** 当前仓储数 */
	private int currNum;
	
	/**
	 * 
	 */
	public Godown()
	{
	}
	
	/**
	 * @param currNum
	 */
	public Godown(int currNum)
	{
		this.currNum = currNum;
	}
	
	/**
	 * 生产产品
	 * 
	 * @param produceNum
	 *            要生产的产品数
	 * 
	 */
	public synchronized void produce(int produceNum)
	{
		if (produceNum < 0)
		{
			return;
		}
		
		// 测试是否需要生产
		while (produceNum + this.currNum > GODOWN_MAX_SIZE)
		{
			System.out.println("要生产的产品数量 " + produceNum + " 超过剩余库存量 " + (GODOWN_MAX_SIZE - this.currNum)
					+ "，暂时不能执行生产任务!");
			
			try
			{
				// 当前的生产线程等待
				this.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// 满足生产条件，则进行生产，这里简单的更改当前库存量
		this.currNum += produceNum;
		System.out.println("已经生产了 " + produceNum + " 个产品，现仓储量为: " + this.currNum);
		// 唤醒在此对象监视器上等待的所有线程
		this.notifyAll();
		
	}
	
	/**
	 * 生产产品
	 * 
	 * @param consumeNum
	 *            要消费的产品数
	 * 
	 */
	public synchronized void consume(int consumeNum)
	{
		if (consumeNum < 0)
		{
			return;
		}
		
		// 测试是否可消费
		while (consumeNum > this.currNum)
		{
			try
			{
				// 暂停此消费线程
				this.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
		}
		
		// 满足消费条件，则进行消费，这里简单的更改当前库存量
		this.currNum -= consumeNum;
		System.out.println("已经消费了 " + consumeNum + "个产品，现仓储量为" + this.currNum);
		// 唤醒在此对象监视器上等待的所有线程
		this.notifyAll();
	}
	
	/**
	 * @return the currNum
	 */
	public int getCurrNum()
	{
		return currNum;
	}
	
	/**
	 * @param currNum
	 *            the currNum to set
	 */
	public void setCurrNum(int currNum)
	{
		this.currNum = currNum;
	}
}
