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

  </head>
  
  <body>
  <s:actionerror/>
  <form action="${pageContext.request.contextPath }/user/login.action" method="post">
	  <span>用户名:</span><input name="user.username" type="text" value="${loginUsername }"><s:fielderror ><s:param>user.username</s:param></s:fielderror><br>
	  <span>密码:</span><input name="user.password" type="password"><s:fielderror ><s:param>user.password</s:param></s:fielderror><br>
	  <input type="submit" value="登录"><span>${msg }</span><br>
	  <input type="checkbox" name="autoLogin" value="autoLogin"><label>自动登录</label>
  </form>
  </body>
  <style type="text/css">
ul {
	margin: 0;
	display: inline-block;
	padding: 0;
	list-style-type: none;
}
</style>
</html>
