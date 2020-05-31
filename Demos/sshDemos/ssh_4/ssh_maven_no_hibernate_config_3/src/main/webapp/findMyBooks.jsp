<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<a href="${pageContext.request.contextPath }/saveBook.jsp">添加书籍</a>
<a href="${pageContext.request.contextPath }/userAction_findAllUser.action">查看其他用户书籍</a>

<jsp:include page="showBooks.jsp"></jsp:include>



</body>
</html>