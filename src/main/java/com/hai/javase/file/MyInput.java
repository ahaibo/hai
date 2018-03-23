/**
 * 
 */
package com.hai.javase.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author as
 *
 */
public class MyInput {

	public static String nextString() {
		InputStreamReader isr = new InputStreamReader(System.in);
		// BufferedInputStream bis=new BufferedInputStream(isr);
		BufferedReader reader = new BufferedReader(isr);
		String s = null;
		try {
			s = reader.readLine();
			while (null == s || s.trim().length() == 0) {
				System.out.println("please input a available string.");
				// s = nextString();
				s = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("input a string op error.");
		}
		System.out.println("input string is: " + s);
		return s;
	}

	/**
	 * @return
	 */
	public static Integer nextInteger() {
		Integer i = null;

		String s = nextString();
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			System.out.println("number format exception...");
			i = nextInteger();
		}
		System.out.println("input integer is: " + i);
		return i;
	}

	public static void main(String[] args) {
		// System.out.println(MyInput.nextString());
		System.out.println(MyInput.nextInteger());
	}
}
