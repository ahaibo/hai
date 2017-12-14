/**
 * 
 */
package com.hai.ijavase.desinpattern.bridge.test;

/**
 * @author Administrator
 * 
 */
public class GrandSon extends Son
{
	private String email;
	
	public GrandSon()
	{
		
	}
	
	/**
	 * @param name
	 * @param sex
	 * @param age
	 * @param sonHobby
	 * @param email
	 */
	public GrandSon(String name, String sex, int age, String sonHobby, String email)
	{
		super(name, sex, age, sonHobby);
		this.email = email;
	}
	
	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param sonHobby
	 * @param email
	 */
	public GrandSon(int id, String name, String sex, int age, String sonHobby, String email)
	{
		super(id, name, sex, age, sonHobby);
		this.email = email;
	}
	
	
	
	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
}
