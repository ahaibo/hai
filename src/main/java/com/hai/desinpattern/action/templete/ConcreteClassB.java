/**
 * 
 */
package com.hai.desinpattern.action.templete;

/**
 * @author Administrator
 * 
 */
public class ConcreteClassB extends AbstractClass {

	private static final String CLASS_NAME = ConcreteClassB.class.getName();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.templete.AbstractClass#doAnything()
	 */
	@Override
	protected void doAnything() {
		System.out.println("doAnything in class: " + CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.templete.AbstractClass#doSomething()
	 */
	@Override
	protected void doSomething() {
		System.out.println("doSomething in class: " + CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.javase.desinpattern.templete.AbstractClass#whetherExecuteMethod()
	 */
	@Override
	protected void whetherExecuteMethod() {
		System.out.println("whetherExecuteMethod in class: " + CLASS_NAME);
	}

	/**
	 * 默认不调用父类的whetherExecuteMethod方法
	 * 
	 * @see AbstractClass#isExecuteMethod()
	 */
	@Override
	protected boolean isExecuteMethod() {
		return false;
	}

}
