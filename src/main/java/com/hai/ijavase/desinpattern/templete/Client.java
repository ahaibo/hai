/**
 * 
 */
package com.hai.ijavase.desinpattern.templete;

/**
 * @author Administrator
 * 
 */
public class Client {

	public static void main(String[] args) {
		AbstractClass concreteClassA = new ConcreteClassA();
		AbstractClass concreteClassB = new ConcreteClassB();

		concreteClassA.templeteMethod();

		System.out.println("--------------------------------------");
		concreteClassB.templeteMethod();
	}
}
