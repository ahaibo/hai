package com.hai.ijavase.thread.multithread.namelist;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试当某对象是线程安全时其操作这些对象的线程间不一定安全
 * 
 * @author Administrator
 * 
 */
public class NameList
{
	private List nameList = Collections.synchronizedList(new LinkedList());
	
	/**
	 * nameList 虽然本身是线程安全的，但多个线程的操作不一定安全，解决办法，在对nameList对象操作的地方加synchronized关键字
	 * 
	 * @param name
	 */
	public synchronized void add(String name)
	{
		nameList.add(name);
	}
	
	public synchronized String removeFirst()
	{
		if (nameList.size() > 0)
		{
			// System.out.println(nameList.size());
			return (String) nameList.remove(0);
		}
		else
		{
			return null;
		}
	}
}