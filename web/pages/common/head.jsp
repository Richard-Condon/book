<%--
  Created by IntelliJ IDEA.
  User: 15298
  Date: 2022/7/28
  Time: 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    pageContext.setAttribute("basePath",basePath);
%>
<base href="http://localhost:8080/book/"><!--一般写道工程目录下-->
<link type="text/css" rel="stylesheet" href="pages/static/css/style.css" >
<script type="text/javascript" src="pages/static/jquery-3.6.0.js"></script>
