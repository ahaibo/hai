/**
 * 
 */
package com.hai.javase.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * 
 */
public class ServletContextTest extends HttpServlet
{
	
	private static final long serialVersionUID = -2380070867620913677L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 通过ServletContext读取properties文件，这种情况不能读取src目录下
		InputStream inputStream = this.getServletContext().getResourceAsStream("/WEB-INF/properties/test.properties");
		
		Properties properties = new Properties();
		properties.load(inputStream);
		
		// 如果properties文件放在src目录下，则用类加载器读取
		// this.getClass().getClassLoader().getResourceAsStream("");
		PrintWriter out = response.getWriter();
		out.print("ServletContextTest");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
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
	
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		super.destroy();
	}
	
}
