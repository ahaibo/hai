/**
 * 
 */
package com.hai.desinpattern.create.factory;

/**
 * @author Administrator
 * 
 */
public class WhiteHuman implements Human
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.factory.Human#getColor()
	 */
	@Override
	public void getColor()
	{
		System.out.println("白人");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.factory.Human#talk()
	 */
	@Override
	public void talk()
	{
		System.out.println("白人普遍说英语");
	}
	
}
