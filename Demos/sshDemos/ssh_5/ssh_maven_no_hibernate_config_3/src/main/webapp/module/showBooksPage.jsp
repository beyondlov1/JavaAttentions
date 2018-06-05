<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>findMyBooks</title>
</head>
<body>



	<table border="1">
		<tbody>

			<c:forEach items="${userBooksPage.contentList}" var="book" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${book.id }</td>
					<td>${book.name }</td>
					<td>${book.price }</td>
					<td>${book.uri }</td>
					<td><a href="${pageContext.request.contextPath }/bookAction_download?filename=${book.fileName}&uri=${book.uri}">${book.fileName }</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table> 

</body>
</html>