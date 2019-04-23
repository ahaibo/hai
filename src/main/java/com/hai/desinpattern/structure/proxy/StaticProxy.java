/**
 * 静态代理示例
 */
package com.hai.desinpattern.structure.proxy;

interface MyInterface {
	void doSomething();
}

class MyImpl implements MyInterface {

	@Override
	public void doSomething() {
		System.out.println(this.getClass().getName() + ".doSomething()...");
	}

}

class MyProx implements MyInterface {

	MyImpl myImpl;

	public MyProx setMyImpl(MyImpl myImpl) {
		this.myImpl = myImpl;
		return this;
	}

	@Override
	public void doSomething() {
		System.out.println("before doSomething method in " + this.getClass().getName());
		this.myImpl.doSomething();
		System.out.println("after doSomething method in " + this.getClass().getName());
	}
}

/**
 * @author as
 *
 */
public class StaticProxy {

	public static void main(String[] args) {
		new MyProx().setMyImpl(new MyImpl()).doSomething();
	}
}
