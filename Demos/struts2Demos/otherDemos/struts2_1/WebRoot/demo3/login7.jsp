<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

  </head>
  
  <body>
  <s:fielderror></s:fielderror>
  <form action="${pageContext.request.contextPath }/demo3/login7.action" method="post">
	  <span>用户名:</span><input name="username" type="text" value="${username }"><br>
	  <span>密码:</span><input name="password" type="password"><br>
	  <span>再次输入密码:</span><input name="repassword" type="password"><br>
	  <span>邮箱:</span><input name="email" type="text"><br>
	  <span>年龄:</span><input name="age" type="text"><br>
	  <span>手机:</span><input name="phone" type="text"><br>
	  
	  <input type="submit" value="登录"><span>${msg }</span> 
  </form>
  </body>
</html>
