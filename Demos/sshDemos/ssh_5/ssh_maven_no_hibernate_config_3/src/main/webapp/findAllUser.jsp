<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>findAllUser</title>
</head>
<body>
<a href="${pageContext.request.contextPath }/bookAction_findMyBooks">我的图书</a>
	<table border="1">
		<tbody>

			<c:forEach items="${allUserList}" var="user" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${user.id }</td>
					<td>${user.username }</td>
					<td>${user.password }</td>
 					<td><a href="${pageContext.request.contextPath }/bookAction_findBooksByUser?user.id=${user.id}">查看他的书籍</a></td>				
            </tr>
			</c:forEach>

		</tbody>
	</table>

</body>
</html>