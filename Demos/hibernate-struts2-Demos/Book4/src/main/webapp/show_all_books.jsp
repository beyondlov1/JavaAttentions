<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'show_all_books' starting page</title>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
tr:NTH-CHILD(even) {
	background: white;
}

tr:NTH-CHILD(odd) {
	background: lightgray;
}

tr:FIRST-CHILD{
background: lightgray;
}

tr~tr:HOVER{
	color:red;
}
</style>

</head>


<body>
	This is my page.
	<br>
	
	<a href="${pageContext.request.contextPath}/add_book.jsp">AddBook</a>

	<table border="0" style="text-align: center; margin: auto">
		<tbody>
			<tr>
				<th>BookId</th>
				<th>BookTitle</th>
				<th>AuthorName</th>
				<th>OwnerName</th>
			</tr>
			<c:forEach items="${list}" var="book">
				<tr>
					<td>${book.id }</td>
					<td>${book.title}</td>
					<td>${book.author.name }</td>
					<td>${book.owner.name }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
