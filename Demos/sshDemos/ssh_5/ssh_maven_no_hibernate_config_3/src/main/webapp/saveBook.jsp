<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>saveBook</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/bookAction_saveBook.action" method="post" enctype="multipart/form-data">
	<input style="display: none;" name="user.id" value="${loginUser.id }">
	name:<input name="book.name" type="text"><br>
	price:<input name="book.price" type="text"><br>
	file:<input name="upload" type="file"><br>
	<input type="submit" value="submit">
	</form>
	
	<s:fielderror></s:fielderror>

</body>
</html>