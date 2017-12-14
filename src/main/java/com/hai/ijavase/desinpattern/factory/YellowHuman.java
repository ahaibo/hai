/**
 * 
 */
package com.hai.ijavase.desinpattern.factory;

/**
 * @author Administrator
 * 
 */
public class YellowHuman implements Human
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.factory.Human#getColor()
	 */
	@Override
	public void getColor()
	{
		System.out.println("黄种人");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.factory.Human#talk()
	 */
	@Override
	public void talk()
	{
		System.out.println("黄种人说话字正腔圆");
	}
	
}
