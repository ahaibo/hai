/**
 * 
 */
package com.hai.desinpattern.structure.bridge.test;

/**
 * @author Administrator
 * 
 */
public class Son extends Father
{
	private String sonHobby;
	
	public Son()
	{
		super();
	}
	
	/**
	 * @param sonHobby
	 */
	public Son(String sonHobby)
	{
		this.sonHobby = sonHobby;
	}
	
	/**
	 * @param name
	 * @param sex
	 * @param age
	 * @param sonHobby
	 */
	public Son(String name, String sex, int age, String sonHobby)
	{
		super(name, sex, age);
		this.sonHobby = sonHobby;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param sonHobby
	 */
	public Son(int id, String name, String sex, int age, String sonHobby)
	{
		super(id, name, sex, age);
		this.sonHobby = sonHobby;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.bridge.test.Father#say()
	 */
	@Override
	public void say()
	{
		System.out.println("Son: Hello,world!");
	}
	
	/**
	 * @return the sonHobby
	 */
	public String getSonHobby()
	{
		return sonHobby;
	}
	
	/**
	 * @param sonHobby
	 *            the sonHobby to set
	 */
	public void setSonHobby(String sonHobby)
	{
		this.sonHobby = sonHobby;
	}
}
