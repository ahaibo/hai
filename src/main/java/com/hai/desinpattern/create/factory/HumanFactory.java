/**
 * 
 */
package com.hai.desinpattern.create.factory;

/**
 * @author Administrator
 * 
 */
public class HumanFactory extends AbstractHumanFactory
{
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hai.hai.javase.desinpattern.factory.AbstractHumanFactory#createHuman()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Human> T createHuman(Class<T> c)
	{
		Human human = null;
		try
		{
			human = (Human) Class.forName(c.getName()).newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return (T) human;
	}
	
}
