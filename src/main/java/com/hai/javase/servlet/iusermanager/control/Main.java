/**
 * 
 */
package com.hai.javase.servlet.iusermanager.control;

import com.hai.common.db.DBUtil;
import com.hai.javase.servlet.common.ServletHelper;
import com.hai.javase.servlet.iusermanager.common.Page;
import com.hai.javase.servlet.iusermanager.entity.User;
import com.hai.javase.servlet.iusermanager.impl.UserDao;
import com.hai.javase.util.StringUtil;
import com.hai.javase.util.VerifyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * 
 */
public class Main extends HttpServlet
{
	
	private static final long serialVersionUID = -2033756706524395294L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ServletHelper.setContentTypeAndEncodingAndNoCache(response);
		
		UserDao userDao = UserDao.newInstance();
		HttpSession session = request.getSession();
		
		delUserById(request, userDao, session);
		
		querAllUsers(userDao, request, session);
		
		response.sendRedirect("/test/iusermanager/main.jsp");
		
	}
	
	/**
	 * 执行删除动作
	 * 
	 * @param request
	 * @param session
	 * @throws IOException
	 */
	private void delUserById(HttpServletRequest request, UserDao userDao, HttpSession session) throws IOException
	{
		String operate = request.getParameter("operate");
		
		if ("del".equals(operate))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			// 执行删除
			String sql = "delete from iuser where id=?";
			int result = userDao.deleteUserById(sql, id);
			String userName = StringUtil.encodingString(request.getParameter("name"));
			String delMsg = "用户 ".concat(userName).concat(" 删除");
			delMsg += result > 0 ? "成功！" : "失败！";
			session.setAttribute("delMsg", delMsg);
			session.setAttribute("delMsgTest", "abc");
		}
		else
		{
			Object obj = session.getAttribute("delMsg");
			if (null != obj)
			{
				session.removeAttribute("delMsg");
			}
		}
	}
	
	/**
	 * 查询所有用户
	 * 
	 * @param request
	 * @param session
	 * @throws IOException
	 */
	private void querAllUsers(UserDao userDao, HttpServletRequest request, HttpSession session) throws IOException
	{
		boolean isRefresh = false;
		String refreshFlag = request.getParameter("isRefresh");
		isRefresh = VerifyUtil.isEmpty(refreshFlag) ? false : Boolean.valueOf(refreshFlag);
		
		Page page = isRefresh ? Page.newInstance(userDao.getDbBase(), "iuser") : setPageInfo(userDao.getDbBase(),
				request);
		
		String sqlPaging = "select * from " + "(select t.*, rownum rn from "
				+ "(select * from iuser order by id) t where rownum <= ?) where rn >= ?";
		int topLimit = page.getPageNo() * page.getPageSize();
		int lowerLimit = (page.getPageNo() - 1) * page.getPageSize() + 1;
		Object[] parmas = { topLimit, lowerLimit };
		
		// String sql = "select * from iuser";
		List<User> users = userDao.getAllUsers(sqlPaging, parmas);
		
		session.setAttribute("users", VerifyUtil.isEmpty(users) ? "当前还没有用户信息！" : users);
		session.setAttribute("page", page);
	}
	
	/**
	 * @param request
	 */
	private Page setPageInfo(DBUtil dbBase, HttpServletRequest request)
	{
		
		String pageNoStr = request.getParameter("pageNo");
		Page sessionPage = (Page) request.getSession().getAttribute("page");
		boolean pageIsNull = null == sessionPage;
		
		int pageNo = Page.DEFAULT_PAGE_NO;
		if (StringUtil.isEmpty(pageNoStr) || VerifyUtil.isNotNumerialNumber(pageNoStr))
		{
			if (!pageIsNull)
			{
				pageNo = pageNo == sessionPage.getPageNo() ? pageNo : sessionPage.getPageNo();
			}
		}
		else
		{
			pageNo = Integer.parseInt(pageNoStr);
		}
		
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize = Page.DEFAULT_PAGE_SIZE;
		if (StringUtil.isEmpty(pageSizeStr) || VerifyUtil.isNotNumerialNumber(pageSizeStr))
		{
			if (!pageIsNull)
			{
				pageSize = pageSize != sessionPage.getPageSize() ? sessionPage.getPageSize() : pageSize;
			}
		}
		else
		{
			pageSize = Integer.parseInt(pageSizeStr);
			pageSize = pageSize > Page.DEFAULT_MAX_PAGE_SIZE ? Page.DEFAULT_MAX_PAGE_SIZE
					: pageSize < Page.DEFAULT_MIN_PAGE_SIZE ? Page.DEFAULT_MIN_PAGE_SIZE : pageSize;
		}
		
		int pageCount = Page.getPageCount(dbBase, "iuser", pageSize);
		
		Page page = Page.newInstance();
		
		page.setPageCount(pageCount);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		return page;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
}
