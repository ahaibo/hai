<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="/static/js/library/jquery-2.0.3.js"></script>
</head>

<body>
	<form action="/Login" method="post" title="iusermanager Login Jsp" style="background-color: lime; width: 500px;">
		<table>
			<tr>
				<td>UserName:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>UserPass:</td>
				<td><input type="password" name="pass" /></td>
			</tr>
			<tr>
				<td align="right" colspan="2">
					<input type="submit" value="Submit" />
					<input type="reset" value="Reset" />
				</td>
			</tr>
		</table>
		<hr>
		<div align="right">
			<button onclick="javascript: window.close();">Close</button>
		</div>
	</form>
</body>
</html>
