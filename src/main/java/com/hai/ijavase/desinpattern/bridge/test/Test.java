/**
 * 
 */
package com.hai.ijavase.desinpattern.bridge.test;

/**
 * @author Administrator
 * 
 */
public class Test
{
	public static void main(String[] args)
	{
		Father person = new GrandSon(100, "grandSon", "MAN", 18, "grandSonHobby: keji!", "grandson@email.com");
		
		person.say();
	}
}
