<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<a href="${param.uri }&currentPageNum=1">首页</a><a href="${param.uri }&currentPageNum=${param.prePageNum}">上一页</a>
<c:forEach begin="${param.displayFirstPageNum }" end="${param.displayLastPageNum }" var="num">
<a href="${param.uri }&currentPageNum=${num}">${num }</a>
</c:forEach>
<a href="${param.uri }&currentPageNum=${param.nextPageNum}">下一页</a><a href="${param.uri }&currentPageNum=${param.totalPageCount}">末页</a>
</body>
</html>