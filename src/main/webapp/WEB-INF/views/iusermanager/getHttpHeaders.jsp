<%@page import="com.hai.javase.servlet.iusermanager.entity.User" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>HTTP HEADERS PAGE</title>

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
    <table>
        <%
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
        %>
        <tr>
            <td class="alignR"><%=header%>:</td>
            <td class="alignL"><%=request.getHeader(header)%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
