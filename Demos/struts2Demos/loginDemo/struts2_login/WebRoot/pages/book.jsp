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
  <a href="${pageContext.request.contextPath }/book/findAllBook.action">查看图书</a>
  <a href="${pageContext.request.contextPath }/book/deleteBook.action">查看图书</a>
  </body>
  
</html>
