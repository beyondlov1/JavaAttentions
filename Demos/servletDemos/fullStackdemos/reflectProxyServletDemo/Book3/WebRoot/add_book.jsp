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
	<form action="${pageContext.request.contextPath }/BookServlet?op=addBookWithFile2" method="post" enctype="multipart/form-data">
		<span>书名</span><input type="text" name="name"> 
		<span>作者</span><input type="text" name="author"> 
		<span>价格</span><input type="text" name="price"> 
		<select name="categoryId">
		<c:forEach items="${category_list }" var="category" varStatus="status">
		<option value="${category.id }" >${category.name }</option>
		</c:forEach>
		</select> 
		<input name="upload" type="file">
		<input type="submit" value="添加"><span>${requestScope.msg}</span>

	</form>
</body>
</html>
