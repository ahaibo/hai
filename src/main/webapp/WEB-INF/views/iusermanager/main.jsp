<%@page import="com.hai.javase.servlet.iusermanager.common.Page" %>
<%@page import="com.hai.javase.servlet.iusermanager.entity.User" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
         contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    Object userObject = session.getAttribute("user");
    if (null == userObject) {
        response.sendRedirect("/iusermanager/login.jsp");
        return;
    }
    User user = (User) userObject;

    Object pageObj = session.getAttribute("page");
    Page pg = (Page) pageObj;
    int pageNo = pg.getPageNo();
    int pageSize = pg.getPageSize();
    int pageCount = pg.getPageCount();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>" target="_self">

    <title>My JSP 'main.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="/static/css/common/info.css">
    <script type="text/javascript" src="/static/js/library/jquery-2.0.3.js"></script>
    <script type="text/javascript" src="/static/js/iusermanager/main.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var delMsgTest =
            <%=session.getAttribute("delMsgTest")%>
            if (null != delMsgTest && delMsgTest.length > 0) {
                alert("delMsgTest: " + delMsgTest);
            }
            var delMsg = "${sessionScope.delMsg}";
            if (null != delMsg && delMsg.trim().length > 0) {
                alert(delMsg);
                <%
                    session.removeAttribute("delMsg");
                %>
            }
        });
    </script>

</head>

<body>
<div>
    <div style="padding-left: 50px;">
        <div>
            <div>
                Welcome user manager <label class="displayUser"><%=user.getName()%>
            </label> login this site!
                <label class="logoutCls" style="padding-left: 64%;">
                    <a href="/WEB-INF/views/iusermanager/login.jsp"> 注销 </a>
                </label>
            </div>
        </div>
    </div>
    <hr color="lightgray" size="8">
    <div style="width: 100%;">
        <div style="margin: 0 50px 0 50px;">
            <div style="margin: 50px 10px 10px 0;">用户列表如下：</div>
            <div>
                <table style="width: 100%;" cellpadding="5" cellspacing="0">
                    <thead>
                    <tr align="left" style="background-color: #387989;">
                        <th style="padding-left: 10px;">姓名</th>
                        <th width="50">性别</th>
                        <th width="50">年龄</th>
                        <th width="100">Email</th>
                        <th>兴趣爱好</th>
                        <th align="right" style="padding-right: 10px;">管理</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object obj = session.getAttribute("users");
                        String msg = null;
                        List<User> users = null;
                        if (obj instanceof String) {
                            msg = (String) obj;
                    %>
                    <tr>
                        <td colspan="6">
                            <center><%=msg%>
                            </center>
                        </td>
                    </tr>
                    <%
                    } else {
                        users = (List<User>) obj;
                        User u = null;
                        for (int i = 0; i < users.size(); i++) {
                            u = users.get(i);
                    %>
                    <tr onmouseover="trMouseOver($('#tr<%=i%>'));" id="tr<%=i%>"
                        onmouseout="trMouseOut($('#tr<%=i%>'));"
                        style="background-color: <%=(i + 1) % 2 == 0 ? "#cccccc" : "#ffffff"%>">
                        <td style="display: none;">
                            <input type="hidden" name="id<%=i%>" id="id<%=i%>" value="<%=u.getId()%>"/>
                            <input type="hidden" name="backBgColor" id="backBgColor"/>
                        </td>
                        <td style="padding-left: 10px;" id="username<%=i%>"><%=u.getName()%>
                        </td>
                        <td><%=u.getSex()%>
                        </td>
                        <td><%=u.getAge()%>
                        </td>
                        <td><%=u.getEmail()%>
                        </td>
                        <td><%=u.getHobby()%>
                        </td>
                        <td align="right" style="padding-right: 10px;">
                            <label>
                                <a href="javascript: manageUser(<%=u.getId()%>);"> 管理 </a>
                            </label>
                            <label>
                                <a href="javascript:deleteUser(<%=u.getId()%>,<%=pageNo%>,<%=pageSize%>,$('#username<%=i%>').text());">删除</a>
                            </label>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                    <tfoot>
                    <tr style="background-color:  #387989; width: 100%;">
                        <td colspan="6">
                            <div>
                                <table>
                                    <tr>
                                        <td align="right">
                                            <div>
                                                <div style="float: right; padding: 0 10px 0 85px;">
                                                    <%
                                                        boolean gtDisplayPageCount = pageCount > 5;
                                                        int cycleNum = gtDisplayPageCount ? 5 : pageCount;
                                                        if (pageNo > 1) {
                                                    %>
                                                    <label><a href="javascript:toSpecificPage(<%=pageNo-1 %>,<%=pageSize %>);">上一页</a></label>
                                                    <%} else {%>
                                                    <label>上一页</label>
                                                    <%} %>
                                                    <%
                                                        for (int i = 1; i <= cycleNum; i++) {
                                                            if (i == pageNo) {
                                                    %>
                                                    <label><<%=i %>></label>
                                                    <%} else { %>
                                                    <label><<a href="/Main?pageNo=<%=i %>"><%=i %> </a>></label>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                    <%
                                                        if (gtDisplayPageCount) {
                                                    %>
                                                    <label>...</label>
                                                    <%} %>
                                                    <%
                                                        if (pageNo < pageCount) {
                                                    %>
                                                    <label><a href="javascript:toSpecificPage(<%=pageNo+1 %>,<%=pageSize %>);">下一页</a></label>
                                                    <%
                                                    } else {
                                                    %>
                                                    <label>下一页</label>
                                                    <%} %>

                                                </div>
                                                <div class="floatL">
                                                    <div>
                                                        <div class="floatLAndPaddingR10">
                                                            每页显示 <input type="text" id="iptPageSize" name="iptPageSize"
                                                                        value="<%= pageSize %>" class="pageIptCls"
                                                                        title="允许最多每页显示20条数据，最少显示2条数据。请输入数值！"> 条数据 -
                                                            <%-- <label onclick="setPageSize($('#iptPageSize').val(),<%=pageNo %>);" style="cursor: pointer;"> - 应用</label> --%>
                                                            <a href="javascript:setPageSize($('#iptPageSize').val(),<%=pageNo %>);">应用</a>
                                                        </div>
                                                        <div class="floatLAndPaddingR10">
                                                            当前页<%=pageNo%>/共<%=pageCount%>页
                                                        </div>
                                                        <div style="float: left;padding-right: 5px;">
                                                            跳转到 <input type="text" id="iptPageNo" name="iptPageNo" value="<%= pageNo %>" class="pageIptCls" onkeydown="" title="请输入数值！"> 页
                                                        </div>
                                                        <div class="floatLAndPaddingR10">
                                                            <!-- <label id="skipPageNoDefaultLabel" style="color: gray;"> GO </label> -->
                                                            <%-- <label id="skipPageNoLabel" style="cursor: pointer;" onclick="skipPageNo($('#iptPageNo').val(),<%=pageSize %>);">
                                                                <!-- <a id="iptPageNoATag" href="/GetHttpHeaders?pageNo=$('#iptPageNo').val()"> GO </a> -->
                                                                GO
                                                            </label> --%>
                                                            <a href="javascript:skipPageNo($('#iptPageNo').val(),<%=pageSize %>);">Go</a>
                                                        </div>
                                                        <div class="floatLAndPaddingR10">
                                                            <!-- <label style="cursor: pointer;" onclick="refreshPage();">刷新</label> -->
                                                            <a href="javascript:refreshPage();">刷新</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<div>
    <h1>File Upload:</h1>
    <form action="/uploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
    </form>
</div>
</body>
</html>
