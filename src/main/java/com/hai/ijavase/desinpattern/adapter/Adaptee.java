/**
 * 
 */
package com.hai.ijavase.desinpattern.adapter;

/**
 * 源角色实现类
 * 
 * @author Administrator
 * 
 */
public class Adaptee implements IAdaptee
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.adapter.IAdaptee#doSomething()
	 */
	@Override
	public void doSomething()
	{
		System.out.println("doSomething execution in class: ".concat(Adaptee.class.getName()));
	}
}
