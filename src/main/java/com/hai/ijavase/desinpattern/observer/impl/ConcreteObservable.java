/**
 * 
 */
package com.hai.ijavase.desinpattern.observer.impl;

import java.util.Observable;

import com.hai.ijavase.desinpattern.observer.service.IObservable;

/**
 * 具体被观察者
 * 
 * @author Administrator
 * 
 */
public class ConcreteObservable extends Observable implements IObservable
{
	@SuppressWarnings("unused")
	private static final String CLASS_NAME = ConcreteObservable.class.getName();
	private static final String SIMPLE_CLASS_NAME = ConcreteObservable.class.getSimpleName();
	
	@Override
	public void doSomething()
	{
		// 注：下面的setChanged()要手动调用才能表明此对象有改变进而调用观察者的update方法，否则就是调用了notifyObservers方法观察者的update方法也不会被调用
		setChanged();
		notifyObservers(SIMPLE_CLASS_NAME + "发起通知！执行doSomething方法啦！！！");
	}
	
	@Override
	public void deAnything()
	{
		setChanged();
		notifyObservers(SIMPLE_CLASS_NAME + "发起通知！执行deAnything方法啦！！！");
	}
	
}
