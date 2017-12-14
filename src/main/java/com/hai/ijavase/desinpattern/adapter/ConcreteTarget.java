/**
 * 
 */
package com.hai.ijavase.desinpattern.adapter;

/**
 * @author Administrator
 * 
 */
public class ConcreteTarget implements Target
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.adapter.Target#request()
	 */
	@Override
	public void request()
	{
		System.out.println("request execution in " + ConcreteTarget.class.getName());
		
	}
	
}
