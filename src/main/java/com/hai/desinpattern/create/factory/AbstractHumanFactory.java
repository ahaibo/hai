/**
 * 
 */
package com.hai.desinpattern.create.factory;

/**
 * @author Administrator
 * 
 */
public abstract class AbstractHumanFactory
{
	/**
	 * 创建人类的抽象方法
	 * 
	 * @return
	 */
	public abstract <T extends Human> T createHuman(Class<T> c);
}
