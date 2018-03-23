/**
 * 
 */
package com.hai.javase.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * 
 */
public class ServletConfigTest extends HttpServlet
{
	
	private static final long serialVersionUID = 8942130229635337273L;
	
	/**
	 * Constructor of the object.
	 */
	public ServletConfigTest()
	{
	}
	
	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		String encoding = this.getServletConfig().getInitParameter("encoding");
		response.setCharacterEncoding(encoding);
		System.out.println("this.getServletConfig().getInitParameter(\"encoding\"): " + encoding);
		
		PrintWriter out = response.getWriter();
		out.print("编码方式为：" + encoding + "<br/>");
		
		out.print("<a href='http://localhost:8080/test/RequestServletTest'>连接到RequestServletTest</a>");
		
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		this.doGet(request, response);
	}
	
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException
	{
		// Put your code here
	}
	
}
