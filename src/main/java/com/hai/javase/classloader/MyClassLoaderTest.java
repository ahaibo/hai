/**
 * 
 */
package com.hai.javase.classloader;

import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class MyClassLoaderTest {

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		// TODO Auto-generated method stub
		//classLoaderTest();

		// System.out.println("MyClassLoaderAttachment: " + new
		// MyClassLoaderAttachment());

		Class<?> clazz = new MyClassLoader("myClassLoaderLib").loadClass("MyClassLoaderAttachment");
		Date my = (Date) clazz.newInstance();
		System.out.println(my);
	}

	/**
	 * 
	 */
	private static void classLoaderTest() {
		// TODO Auto-generated method stub
		System.out.println("SystemClassLoader: " + ClassLoader.getSystemClassLoader().getClass().getName());

		ClassLoader classLoader = MyClassLoaderTest.class.getClassLoader();
		if (null != classLoader) {
			System.out.println("MyClassLoaderTest ClassLoader: " + classLoader.getClass().getName());
			do {
				classLoader = classLoader.getParent();
				if (null != classLoader) {
					System.out.println("\nMyClassLoaderTest Parent ClassLoader: " + classLoader.getClass().getName());
				}
			} while (null != classLoader);
		}

	}
}
