<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         isThreadSafe="true" contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>DRAW CODE PAGE</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
</head>

<body>
<%--<p><a href="draw-code">Draw Code</a></p>
<p><a href="/WEB-INF/views/iusermanager/login.jsp">TO iusermanager</a></p>
<p><a href="/WEB-INF/views/fileupdownload/smartUpload.jsp">TO SmartUpload</a></p>--%>
<p><a href="/test">Test</a></p>
<p><a href="/draw-code">Draw Code</a></p>
<p><a href="/userManage/login">TO iusermanager</a></p>
<p><a href="/smart-upload">TO SmartUpload</a></p>
</body>
</html>
