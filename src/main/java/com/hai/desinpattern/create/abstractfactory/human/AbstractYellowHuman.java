/**
 * 
 */
package com.hai.desinpattern.create.abstractfactory.human;

/**
 * @author Administrator
 */
public abstract class AbstractYellowHuman implements Human {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.abstractfactory.Human#color()
	 */
	@Override
	public void color() {
		// TODO Auto-generated method stub
		System.out.println("黄种人");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.abstractfactory.Human#talk()
	 */
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("黄种人说话：吃了吗？。。");
	}
}
