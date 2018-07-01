<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'modifyBook.jsp' starting page</title>

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
<form action="${pageContext.request.contextPath }/book/modifyBook.action" method="post" enctype="multipart/form-data">
    <input type="text" name="id" style="display:none" value="${book.id }">
    名称:<input type="text" name="name" value="${book.name }"><br>
    价格:<input type="text" name="price" value="${book.price }"><br>
    作者:<input type="text" name="author.name" value="${book.author.name }"><br>
    文件:<input type="file" name="file" value="file">
    封面:<input type="file" name="cover" value="cover">
    <input type="submit" value="修改">
</form>
</body>

</html>
