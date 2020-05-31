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
	<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.6/jquery.min.js"></script>

<style type="text/css" >
/*
td{
display: block;
}

tr:NTH-CHILD(even) {
	background: orange;
	position: relative;
	left:-100%;
}

tr:NTH-CHILD(odd) {
	background: lightgray;
	position:relative;
	right:-100%;
}
*/
tr:NTH-CHILD(even) td {
	background: orange;
	position: relative;
	left:-100%;
}

tr:NTH-CHILD(odd) td {
	background: lightgray;
	position:relative;
	right:-100%;
}

tr:FIRST-CHILD{
background: lightgray;
}

tr~tr:HOVER:NTH-CHILD(even){
	color: red;
}
tr~tr:HOVER:NTH-CHILD(odd){
	color:fuchsia ;
}
</style>

</head>


<body>
	This is my page.
	<br>

	<a href="${pageContext.request.contextPath}/add_book.jsp">AddBook</a>

	<table border="0" style="text-align: center; margin: auto;overflow: hidden;" >
		<tbody>
			<tr>
				<th>BookId</th>
				<th>BookTitle</th>
				<th>AuthorName</th>
				<th>OwnerName</th>
			</tr>
			<c:forEach items="${list}" var="book">
				<tr onclick="select(this)">
					<td >${book.id }</td>
					<td>${book.title}</td>
					<td>${book.author.name }</td>
					<td>${book.owner.name }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div style="background: red;width: 100px;height: 100px;position: relative;" onclick="select(this)"></div>
</body>

<script type="text/javascript">
$(function(){
$("td").animate({"left":"0","right":"0"}, 1000);
})

function select(e){
$("tr:nth-child(odd) td").not($(e).children("td")).animate({"left":"-100%","right":"-100%"}, 1000);
$("tr:nth-child(even) td").not($(e).children("td")).animate({"left":"100%","right":"100%"}, 1000);
}
</script>
</html>
