/**
 * 
 */
package com.hai.ijavase.desinpattern.singleton;

/**
 * @author Administrator
 * 
 */
public class Singleton {

	private static volatile Singleton instane;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (null == instane) {
			synchronized (Singleton.class) {
				if (null == instane) {
					instane = new Singleton();
				}
			}
		}
		return instane;
	}

	public void doSomething() {
		System.out.println(this.getClass().getName() + ".doSomething()...");
	}
}
