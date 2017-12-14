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
    <style type="text/css">
        .code-item {
            float: left;
            margin: 10px;
        }
    </style>
</head>

<body>
<div class="code-item">
    <p>DrawLine:</p>
    <p><img src="/graphic/line" alt="draw line"></p>
</div>
<div class="code-item">
    <p>Random DrawLine:</p>
    <p><img src="/graphic/line/random" alt="draw line"></p>
</div>
</body>
</html>
