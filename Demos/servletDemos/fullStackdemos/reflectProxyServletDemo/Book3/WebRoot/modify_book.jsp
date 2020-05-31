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
	<form action="${pageContext.request.contextPath }/BookServlet?op=modifyBookWithFile2" method="post" enctype="multipart/form-data">
	    <!-- 隐藏参数 -->
		<input type="text" name="id" value="${requestScope.book.id }" style="display:none">
		
		
		<span>名称</span><input type="text" name="name" value="${requestScope.book.name }"> 
		<span>作者</span><input type="text" name="author" value="${requestScope.book.author }">
		<span>价格</span><input type="text" name="price" value="${requestScope.book.price }">
		<select name="categoryId">
		<c:forEach items="${requestScope.category_list }" var="category" varStatus="status">
		
		<c:if test="${category.name==requestScope.book.category.name }">
		    <option value="${category.id}" selected="selected">${category.name}</option>
		</c:if>
		<c:if test="${category.name!=requestScope.book.category.name}">
		    <option value="${category.id}">${category.name}</option>
		</c:if>
		</c:forEach>
		</select>
		<input type="file" name="upload">
		<input type="submit" value="修改"><span>${requestScope.msg}</span>
	</form>
  </body>
</html>
