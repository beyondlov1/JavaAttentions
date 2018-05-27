<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'add_book.jsp' starting page</title>

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
	<form action="${pageContext.request.contextPath }/CategoryServlet" method="get">
	    <!-- 隐藏参数 -->
	    <input type="text" name="op" value="modifyCategory" style="display:none">
		<input type="text" name="id" value="${requestScope.category.id }" style="display:none">
		
		<span>分类名称</span><input type="text" name="name" value="${requestScope.category.name }"> 
		<span>描述</span><input type="text" name="description" value="${requestScope.category.description }">
		<input type="submit" value="修改"><span>${requestScope.msg}</span>
		
	</form>
</body>
</html>
