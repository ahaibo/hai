package com.hai.ijavase.thread.producerandconsumer.impl1;

/**
 * 产品
 * 
 * @author Administrator
 * 
 */
class Mantou
{
	private int id;
	
	Mantou(int id)
	{
		this.id = id;
	}
	
	public String toString()
	{
		return "Mantou" + id;
	}
}