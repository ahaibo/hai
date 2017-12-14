/**
 * 
 */
package com.hai.ijavase.iservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author Administrator
 * 
 */
public class ThreadServlet extends HttpServlet implements Runnable
{
	
	private static final long serialVersionUID = 4968379723393140009L;
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				System.out.println("refresh this servletContext..");
				Thread.sleep(2000);
			}
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException
	{
		//new Thread(new ThreadServlet()).main();
	}
	
}
