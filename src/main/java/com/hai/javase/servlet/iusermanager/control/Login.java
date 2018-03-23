/**
 *
 */
package com.hai.javase.servlet.iusermanager.control;

import com.alibaba.fastjson.JSONObject;
import com.hai.common.Constants;
import com.hai.javase.servlet.iusermanager.entity.User;
import com.hai.javase.util.StringUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author Administrator
 */
public class Login extends HttpServlet {

    private static final long serialVersionUID = -9209049353744185994L;
    private ServletConfig servletConfig;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.servletConfig = config;
        Enumeration<String> parameterNames = config.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println("paramName: " + paramName);
        }
        //获取web.xml配置的init-param参数
        String encoding = config.getInitParameter("encoding");
        System.out.println("encoding: " + encoding);

        servletContext = config.getServletContext();
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        servletContext.getVirtualServerName();
        servletContext.getInitParameter("encoding");
        servletContext.getServerInfo();
        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
        System.out.println("sessionCookieConfig\t" + JSONObject.toJSONString(sessionCookieConfig, true));

        //在不同servlet间传输参数
        servletContext.setAttribute("name", new Object());
        servletContext.getAttribute("name");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 防盗链
        if (!checkLinkIllegal(request, response)) {
            return;
        }

        // 用户登录校验
        String userName = StringUtil.encodingString(request.getParameter("name"));
        String userPass = request.getParameter("pass");

        if (Constants.ADMIN_NAME.equals(userName) && Constants.ADMIN_PASS.equals(userPass)) {
            User user = new User();
            user.setName(userName);
            user.setPass(userPass);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(Constants.IUSERMANAGER_HTTP_HEADERS_PAGE);
        } else {
            response.sendRedirect(Constants.IUSERMANAGER_INDEX_PAGE);
        }

    }

    /**
     * @param request
     * @param response
     * @throws IOException
     */
    private boolean checkLinkIllegal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String referer = request.getHeader("Referer");
        if (StringUtil.isNull(referer)
                || !(referer.startsWith(Constants.REFERER_PREFIX1) || referer.startsWith(Constants.REFERER_PREFIX2))) {
            response.sendRedirect(Constants.IUSERMANAGER_INDEX_PAGE);
            return false;
        }

        return true;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

}
