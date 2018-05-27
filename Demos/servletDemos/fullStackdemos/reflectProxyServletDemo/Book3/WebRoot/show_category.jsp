<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'books' starting page</title>

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
<a href="${pageContext.request.contextPath }/CategoryServlet?op=initAddCategory">添加</a>
	<table border="1">
		<tbody>
			<tr>
				<th colspan="5">categorys</th>
			</tr>
			<c:forEach items="${categoryList }" var="category" varStatus="status">
				<tr>
				    <td>${status.count }</td>
					<td>${category.name}</td>
					<td>${category.description }</td>
					<td><a href="${pageContext.request.contextPath }/CategoryServlet?op=initModifyCategory&id=${category.id}">修改</a></td>
					<td><a href="${pageContext.request.contextPath }/CategoryServlet?op=removeCategory&id=${category.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</body>

</html>
