<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    This is my JSP page. <br>
    <form action="${pageContext.request.contextPath }/book/addBook.action" method="post" enctype="multipart/form-data">
    <input type="text" name="owner.id" style="display:none">
    名称:<input type="text" name="name"><br>
    价格:<input type="text" name="price"><br>
    作者:<input type="text" name="author.name"><br>
    <input type="file" name="file" value="file">
    <input type="file" name="cover" value="cover">
    <input type="submit" value="添加">
    </form>
  </body>
  
  <script type="text/javascript">
  $(function(){
  $("input:first").val(getLoginUserId());
  })
  function getLoginUserId(){
  var userId = null;
  var myCookie = document.cookie;
  var cookieArray = myCookie.split("; ");
  for(var i = 0; i<cookieArray.length;i++){
  var cookieName = cookieArray[i].split("=")[0];
  if(cookieName=="loginUserId"){
  userId=cookieArray[i].split("=")[1];
  }
  }
  return userId;
  }
  </script>
</html>
