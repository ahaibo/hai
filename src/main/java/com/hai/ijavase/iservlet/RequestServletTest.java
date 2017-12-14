/**
 * 
 */
package com.hai.ijavase.iservlet;

import com.hai.ijavase.util.PrintUtil;
import com.hai.ijavase.util.StringUtil;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * 
 */
public class RequestServletTest extends HttpServlet {

	private static final long serialVersionUID = 5531237333477795274L;

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String requestParamName = request.getParameter("name");
		System.out.println("request.getParameter(\"name\"): " + StringUtil.encodingString(requestParamName));

		// 打印Request相关信息
		printRequestInfo(request);

		String host = request.getHeader("Host");
		System.out.println("host: " + host);

		// 防止盗链示例
		String referer = request.getHeader("Referer");
		System.out.println("referer: " + referer);

		if (null == referer || !referer.trim().startsWith("http://localhost:8080/test")) {
			response.sendRedirect("/hai/RefererError");
			return;
		}

		out.print("Host: " + host + "<br/>");
		out.print("Referer: " + referer);
	}

	/**
	 * 打印请求的相关信息
	 * 
	 * @param request
	 */
	private void printRequestInfo(HttpServletRequest request) {

		Map<Object, Object> map = new HashMap<>();

		printAsyncContextSimpleInfo(map);

		printRequestSimpleInfo(request, map);

		printHeaders(request);
	}

	/**
	 * @param request
	 */
	private void printHeaders(HttpServletRequest request) {

		Enumeration<String> headers = request.getHeaderNames();

		System.out.println("\nHttpServletRequest All Headers Info As Below: \n");

		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			System.out.println(header + " = " + request.getHeader(header));
		}
	}

	/**
	 * 打印Request简要信息
	 * 
	 * @param request
	 * @param map
	 */
	private void printRequestSimpleInfo(HttpServletRequest request, Map<Object, Object> map) {
		map.clear();
		map.put("request.getAuthType()", request.getAuthType());
		map.put("request.getCharacterEncoding()", request.getCharacterEncoding());
		map.put("request.getContentLength()", request.getContentLength());
		map.put("request.getContentType()", request.getContentType());
		map.put("request.getCookies()", request.getCookies());
		map.put("request.getDispatcherType()", request.getDispatcherType());
		map.put("request.getHeaderNames()", request.getHeaderNames());
		map.put("request.getLocalAddr()", request.getLocalAddr());
		map.put("request.getLocale()", request.getLocale());
		map.put("request.getLocales()", request.getLocales());
		map.put("request.getLocalName()", request.getLocalName());
		map.put("request.getLocalPort()", request.getLocalPort());
		map.put("request.getPathInfo()", request.getPathInfo());
		map.put("request.getMethod()", request.getMethod());
		map.put("request.getPathTranslated()", request.getPathTranslated());
		map.put("request.getRemoteAddr()", request.getRemoteAddr());
		map.put("request.getRemoteHost()", request.getRemoteHost());
		map.put("request.getRemotePort()", request.getRemotePort());
		map.put("request.getRemoteUser()", request.getRemoteUser());// 该方法返回通过身份验证的用户名
		map.put("request.getUserPrincipal()", request.getUserPrincipal());// 该方法返回一个java.security.Principal对象，该对象包含了通过身份验证的用户的用户名
		map.put("request.getRequestedSessionId()", request.getRequestedSessionId());
		map.put("request.getRequestURL()", request.getRequestURL());
		map.put("request.getContextPath()", request.getContextPath());
		map.put("request.getProtocol()", request.getProtocol());
		map.put("request.getRequestURI()", request.getRequestURI());
		map.put("request.getQueryString()", request.getQueryString());
		map.put("request.getScheme()", request.getScheme());
		map.put("request.getServerName()", request.getServerName());
		map.put("request.getServerPort()", request.getServerPort());
		map.put("request.getServletContext()", request.getServletContext());
		map.put("request.getServletPath()", request.getServletPath());
		try {
			map.put("request.getReader()", request.getReader());
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		PrintUtil.printMap("\nRequest Simple Info As Below: ", map);
	}

	/**
	 * 打印 AsyncContext 类的几个属性信息
	 */
	private void printAsyncContextSimpleInfo(Map<Object, Object> map) {

		String asyncContextPath = AsyncContext.ASYNC_CONTEXT_PATH;
		String asyncPathInfo = AsyncContext.ASYNC_PATH_INFO;
		String asyncQueryString = AsyncContext.ASYNC_QUERY_STRING;
		String asyncRequestURI = AsyncContext.ASYNC_REQUEST_URI;
		String asyncServletPath = AsyncContext.ASYNC_SERVLET_PATH;

		map.put("asyncContextPath", asyncContextPath);
		map.put("asyncPathInfo", asyncPathInfo);
		map.put("asyncQueryString", asyncQueryString);
		map.put("asyncRequestURI", asyncRequestURI);
		map.put("asyncServletPath", asyncServletPath);

		PrintUtil.printMap("\nAsyncContext Class Properties Simple Info As Below: ", map);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
