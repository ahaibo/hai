<%@page import="com.hai.javase.servlet.iusermanager.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	Object userObject = session.getAttribute("queryUser");
	if (null == userObject)
	{
		response.sendRedirect("/hai/WEB-INF/iusermanager/login.jsp");
		return;
	}
	User user = (User) userObject;
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户详细信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="/static/css/common/info.css">
<script type="text/javascript" src="/static/js/library/jquery-2.0.3.js"></script>
</head>

<body>
	<div>
		<div style="padding: 10px 30px 30px 50px; ">
			<div>
				<h3>用户  <label class="displayUser"><%=user.getName()%></label> 信息如下：</h3>
			</div>
			<div>
				<table>
					<tbody>
						<tr>
							<th align="right">姓名：</th>
							<td><%=user.getName()%></td>
						</tr>
						<tr>
							<th align="right">性别：</th>
							<td><%=user.getSex()%></td>
						</tr>
						<tr>
							<th align="right">年龄：</th>
							<td><%=user.getAge()%></td>
						</tr>
						<tr>
							<th align="right">Email：</th>
							<td><%=user.getEmail()%></td>
						</tr>
						<tr>
							<th align="right">兴趣爱好：</th>
							<td><%=user.getHobby()%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div>
		<div>
			<hr>
			<div>
				<div style="float: right; padding-right: 100px;">
					<button onclick="javascript: window.close();">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
