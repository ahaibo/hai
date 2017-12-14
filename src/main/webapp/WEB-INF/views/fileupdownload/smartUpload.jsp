<%@ page import="java.net.URL" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.io.File" %>
<%@ page import="com.hai.common.Constants" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/6
  Time: 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SmartUpload</title>
    <style>
        .item {
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div>
    <h1>IMGS</h1>
    <%
        URL url = request.getServletContext().getResource(Constants.FILE_UPLOAD_PATH);
    %>
    <div>
        <div><%=JSONObject.toJSONString(url, true)%>
        </div>
        <%
            File file = new File(url.getPath());
            if (null != file && file.exists()) {
                File[] files = file.listFiles();
                for (File f : files) {
        %>
        <div class="floatL img-item">
            <div><img src="<%="/static/images/"+f.getName()%>" width="350" height="300"></div>
            <div><a name="download" class="btn-download" f-name="<%=f.getName()%>" href="/smart/download?file=<%=f.getName()%>">Download</a></div>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
<div class="item">
    <h1>File Upload</h1>
    <form action="/uploadServlet" enctype="multipart/form-data" method="post">
        <input type="file" name="file">
        <input type="submit">
    </form>
</div>

<div class="item">
    <h1>JspSmart File Upload</h1>
    <form action="/smart/upload" enctype="multipart/form-data" method="post">
        <input type="file" name="file">
        <input type="submit">
    </form>
</div>
<div class="item">
    <h1>JspSmart File Download</h1>
    <form action="/smart/download" method="post">
        <input type="text" name="file" value="1e30e924b899a9019ab753bd17950a7b0308f5bf.jpg">
        <input type="submit" value="Download">
    </form>
</div>
<script src="/static/js/library/jquery-2.0.3.js"/>
<script type="text/javascript">
    $(function () {
        $(".btn-download").click(function () {
            $.post("/smart/download", {f: $(this).attr("f-name")}, function () {
                console.log("success")
            }, function () {
                console.log("fail")
            });
        });
    })
</script>
</body>
</html>
