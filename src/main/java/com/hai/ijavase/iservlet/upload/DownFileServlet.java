/**
 * 
 */
package com.hai.ijavase.iservlet.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Administrator
 * 
 */
@WebServlet("/downFileServlet")
public class DownFileServlet extends HttpServlet {

	private static final long serialVersionUID = 1843597890693939521L;

	/**
	 * 用Servlet演示文件下载功能
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

		response.setContentType("text/html");
		String filename = "wr.jpg";
		response.setHeader("Content-Disposition", "attachment; filename=".concat(filename));
		// 获取文件的绝对路径
		String fileDir = "/Images/";
		String filePath = fileDir.concat(filename);
		String fileRealPath = this.getServletContext().getRealPath(filePath);

		FileInputStream fis = new FileInputStream(fileRealPath);

		byte[] buffer = new byte[1024];
		int len = 0;

		while ((len = fis.read(buffer)) > 0) {
			response.getOutputStream().write(buffer, 0, len);
		}

		if (null != fis) {
			fis.close();
			fis = null;
		}
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
