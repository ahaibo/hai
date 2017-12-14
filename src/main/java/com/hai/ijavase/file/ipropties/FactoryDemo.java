/**
 * 
 */
package com.hai.ijavase.file.ipropties;

/**
 * @author Administrator
 * 
 */
public class FactoryDemo
{
	private static final FactoryDemo instance = new FactoryDemo();
	private DaoDemo daoDemo = null;
	
	private FactoryDemo()
	{
		System.out.println(this.getClass().getName() + " be create..");
		setDaoDemo(new DaoDemo());
		System.out.println("daoDemo: " + daoDemo);
	}
	
	public static FactoryDemo getInstance()
	{
		return instance;
	}
	
	/**
	 * @return the daoDemo
	 */
	public DaoDemo getDaoDemo()
	{
		return daoDemo;
	}
	
	/**
	 * @param daoDemo
	 *            the daoDemo to set
	 */
	public void setDaoDemo(DaoDemo daoDemo)
	{
		this.daoDemo = daoDemo;
	}
}
