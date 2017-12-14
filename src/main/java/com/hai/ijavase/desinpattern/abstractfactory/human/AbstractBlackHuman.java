/**
 * 
 */
package com.hai.ijavase.desinpattern.abstractfactory.human;

/**
 * @author Administrator
 */
public abstract class AbstractBlackHuman implements Human {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.abstractfactory.Human#color()
	 */
	@Override
	public void color() {
		// TODO Auto-generated method stub
		System.out.println("黑人");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.abstractfactory.Human#talk()
	 */
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("黑人说话：！@#￥%……&...");
	}
}
