/**
 * 
 */
package com.hai.desinpattern.create.abstractfactory.human;

/**
 * @author Administrator
 */
public abstract class AbstractBlackHuman implements Human {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.abstractfactory.Human#color()
	 */
	@Override
	public void color() {
		// TODO Auto-generated method stub
		System.out.println("黑人");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.abstractfactory.Human#talk()
	 */
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("黑人说话：！@#￥%……&...");
	}
}
