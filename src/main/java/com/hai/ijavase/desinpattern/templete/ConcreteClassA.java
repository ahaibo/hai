/**
 * 
 */
package com.hai.ijavase.desinpattern.templete;

/**
 * @author Administrator
 * 
 */
public class ConcreteClassA extends AbstractClass {

	private static final String CLASS_NAME = ConcreteClassA.class.getName();

	/** 假设ConcreteClassA类需要用户指定父类的whetherExecuteMethod方法是否被调用，默认调用 */
	private boolean isExecuteMethod = true;

	public ConcreteClassA() {

	}

	/**
	 * @param isExecuteMethod
	 */
	public ConcreteClassA(boolean isExecuteMethod) {
		this.isExecuteMethod = isExecuteMethod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.templete.AbstractClass#doAnything()
	 */
	@Override
	protected void doAnything() {
		System.out.println("doAnything in class: " + CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.templete.AbstractClass#doSomething()
	 */
	@Override
	protected void doSomething() {
		System.out.println("doSomething in class: " + CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hai.hai.ijavase.desinpattern.templete.AbstractClass#whetherExecuteMethod()
	 */
	@Override
	protected void whetherExecuteMethod() {
		System.out.println("whetherExecuteMethod in class: " + CLASS_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.ijavase.desinpattern.templete.AbstractClass#isExecuteMethod()
	 */
	@Override
	protected boolean isExecuteMethod() {
		return this.isExecuteMethod;
	}

	/**
	 * @param isExecuteMethod
	 *            the isExecuteMethod to set
	 */
	public void setExecuteMethod(boolean isExecuteMethod) {
		this.isExecuteMethod = isExecuteMethod;
	}

}
