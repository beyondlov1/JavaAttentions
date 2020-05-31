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

<title>My JSP 'addBook.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>

<body>
	This is my JSP page.
	<br> ${msg}
	<form
		action="${pageContext.request.contextPath }/order/addOrder.action" method="post">
		<input style="display:none" name="book.id" value="${bookId}">
		<input style="display:none" name="borrower.id" value="${borrowerId}">
		
		<select name="exchangeBook.id">
			<c:forEach items="${ownerBooks }" var="book">
				<option value="${book.id }">${book.name}</option>
			</c:forEach>
		</select>
		<input type="submit" value="提交">
	</form>
</body>


<script type="text/javascript">
 
  </script>
</html>
