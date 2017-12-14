/**
 * 
 */
package com.hai.ijavase.util.regex;

/**
 * @author Administrator
 * 
 */
public class RegexReplaceTest
{
	public static void main(String[] args)
	{
		String[] strs = { "1aaa", "2aaa", "3aaa", "123aaa", "23aaa", "01aaa" };
		
		for (int i = 0; i < strs.length; i++)
		{
			String str = strs[i];
			str = str.replaceAll("(\\d+)", "00$1");
			str = str.replaceAll("0*(\\d{3})", "$1");
			strs[i] = str;
		}
		
		for (String string : strs)
		{
			System.out.println(string);
		}
	}
}
