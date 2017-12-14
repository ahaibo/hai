/**
 * 
 */
package com.hai.ijavase.inetwork.socket.encrypt;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class User implements Serializable
{
	
	private static final long serialVersionUID = 4009546893505350709L;
	private String name;
	private String password;
	
	/**
	 * @param string
	 * @param string2
	 */
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
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
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
