package com.hai.javase.desinpattern.bridge.test;

/**
 * @author Administrator
 * 
 */
public class Father
{
	private int id;
	private String name;
	private String sex;
	private int age;
	
	public Father()
	{
	}
	
	/**
	 * @param name
	 * @param sex
	 * @param age
	 */
	public Father(String name, String sex, int age)
	{
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 */
	public Father(int id, String name, String sex, int age)
	{
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	
	public void say()
	{
		System.out.println("Hello,world!");
	}
	
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return the sex
	 */
	public String getSex()
	{
		return sex;
	}
	
	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	
	/**
	 * @return the age
	 */
	public int getAge()
	{
		return age;
	}
	
	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age)
	{
		this.age = age;
	}
	
}
