<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div><a href="${pageContext.request.contextPath }/bookAction_findMyBooksPage.action">MyBook</a></div>

<div>${userBooksPage.currentPageNum }</div>

<jsp:include page="/module/showBooksPage.jsp"></jsp:include>

<jsp:include page="/module/pageFoot.jsp">
<jsp:param value="${userBooksPage.displayFirstPageNum }" name="displayFirstPageNum"/>
<jsp:param value="${userBooksPage.displayLastPageNum }" name="displayLastPageNum"/>
<jsp:param value="${userBooksPage.uri }" name="uri"/>
<jsp:param value="${userBooksPage.nextPageNum }" name="nextPageNum"/>
<jsp:param value="${userBooksPage.prePageNum }" name="prePageNum"/>
<jsp:param value="${userBooksPage.totalPageCount }" name="totalPageCount"/>
</jsp:include>
</body>
</html>