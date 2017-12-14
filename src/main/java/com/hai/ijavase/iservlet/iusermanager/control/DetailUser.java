/**
 * 
 */
package com.hai.ijavase.iservlet.iusermanager.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hai.ijavase.iservlet.common.ServletHelper;
import com.hai.ijavase.iservlet.iusermanager.entity.User;
import com.hai.ijavase.iservlet.iusermanager.impl.UserDao;
import com.hai.ijavase.util.VerifyUtil;

/**
 * @author Administrator
 * 
 */
public class DetailUser extends HttpServlet
{
	
	private static final long serialVersionUID = 8943960474426072732L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletHelper.setContentTypeAndEncodingAndNoCache(response);
		HttpSession session = request.getSession();
		
		User user = null;
		String userIdStr = request.getParameter("userId");
		int userId = VerifyUtil.isNotEmpty(userIdStr) && VerifyUtil.isNumerialNumber(userIdStr) ? Integer
				.parseInt(userIdStr) : 0;
		
		String failedQueryUserMsg = "查询该用户的信息为空！";
		if (userId == 0)
		{
			session.setAttribute("queryUser", failedQueryUserMsg);
		}
		else
		{
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) session.getAttribute("users");
			
			if (VerifyUtil.isNotEmpty(users))
			{
				for (User u : users)
				{
					if (u.getId() == userId)
					{
						user = u;
						break;
					}
				}
			}
			else
			{
				user = UserDao.newInstance().getUserById(userId);
			}
			
			session.setAttribute("queryUser", VerifyUtil.isEmpty(user) ? failedQueryUserMsg : user);
		}
		
		response.sendRedirect("/test/iusermanager/detailUser.jsp");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
}
