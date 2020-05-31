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
<a href="${pageContext.request.contextPath }/BookServlet?op=initAddBook">添加</a>

	<table border="1">
	<!--<caption style="border:1px soild black;background-color:green;color:red">book</caption>--><!-- 表格标题 -->
		<tbody>
			<thead>
			<tr>
				<th colspan="10">books</th>
			</tr>
			</thead>
			<c:forEach items="${page.contentList }" var="book" varStatus="status">
				<tr>
				    <td>${status.count }</td>
					<td>${book.name}</td>
					<td>${book.author }</td>
					<td>${book.price }</td>
					<td>${book.category.name }</td>
					<td><a href="${pageContext.request.contextPath }/BookServlet?op=initModifyBook&id=${book.id}">修改</a></td>
					<td><a href="${pageContext.request.contextPath }/BookServlet?op=removeBook&id=${book.id}">删除</a></td>
					<td><a href="${pageContext.request.contextPath }/${book.fileUri}">${book.fileName }</a></td>
					<td><a href="${pageContext.request.contextPath }/CartServlet?op=putInCart&id=${book.id}&currentPageNum=${page.currentPageNum}">加入购物车</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="splitPageFoot.jsp"></jsp:include>
	<div id="cartContainer" style="position:fixed;right:0;bottom: 0;width:25%">
	<div id="cart"></div>
	<button onclick='toggle()' style="width: 100%;">toggle</button>
	</div>
</body>

<script type="text/javascript">

function getUrl(){
return window.location;
}

function toggle(){
if(document.getElementById("cart").style.display=="none"){
document.getElementById("cart").style.display="block";
}else{
document.getElementById("cart").style.display="none";
}
}


var xmlRequest = new XMLHttpRequest();
xmlRequest.onreadystatechange = function(){
if(xmlRequest.readyState == 4 && xmlRequest.status == 200){
var result = xmlRequest.responseText;
document.getElementById("cart").innerHTML = result;
}
}

function getCart(){
var context = "${pageContext.request.contextPath}";
xmlRequest.open("POST",context+"/CartServlet?op=showAllCart",true);
xmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//post一定要加
xmlRequest.send();
}

window.onload=getCart();

</script>
</html>
