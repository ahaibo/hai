/**
 * 
 */
package com.hai.ijavase.desinpattern.abstractfactory.human;

/**
 * @author Administrator
 */
public abstract class AbstractWhiteHuman implements Human {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.abstractfactory.Human#color()
	 */
	@Override
	public void color() {
		// TODO Auto-generated method stub
		System.out.println("白人");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.abstractfactory.Human#talk()
	 */
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("白人说话：english..");
	}
}
