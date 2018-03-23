/**
 * 模板方法模式的优点： 1、封装不变部分，扩展可变部分 2、提取公共部分的代码，便于维护 3、行为由父类定义儿子类实现
 */
package com.hai.javase.desinpattern.templete;

/**
 * 模板方法模式原理解析
 * 
 * @author Administrator
 * 
 */
public abstract class AbstractClass
{
	
	/**
	 * 模板方法的基本方法，抽象，最后定义为protected类型的
	 */
	protected abstract void doAnything();
	
	protected abstract void doSomething();
	
	/**
	 * 模板方法中是否要调用的方法
	 */
	protected abstract void whetherExecuteMethod();
	
	/**
	 * 模板类中一般存在一个钩子方法[Hook Method]子类覆写，用于指示模板方法模式中的某一方法是否被调用。一般带有默认值
	 * 
	 * @return 返回boolean值，只是方法whetherExecuteMethod是否被调用。默认被调用
	 */
	protected boolean isExecuteMethod()
	{
		return true;
	}
	
	/**
	 * 模板方法。此方法在模板类中实现，其实现一般是按一定的规则和顺序调用本类方法，并且此方法为了防止子类覆盖一般声明为final方法
	 * 注：模板方法可以有多个
	 */
	final void templeteMethod()
	{
		this.doSomething();
		this.doAnything();
		if (this.isExecuteMethod())
		{
			this.whetherExecuteMethod();
		}
	}
	
}
