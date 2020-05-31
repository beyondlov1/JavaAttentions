<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 下面三行很重要 -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

  </head>
  
  <body>
  
   <%-- <form action="${pageContext.request.contextPath }/user/register.action" method="post">
    <table border="1" align="center">
    <tbody>
    <tr><td>用户名:</td><td><input name="username" type="text"></td></tr>
    <tr><td>密码:</td><td><input name="password" type="password"></td></tr>
    <tr style="text-align:center"><td colspan="2"><input type="submit" value="注冊"></td></tr>
    </tbody>
    </table>
   </form> --%>
   
   <h3 class="" style="position:absolute;
	width:500px;
	height:40px;
	margin:auto;
	text-align:center;
	margin:auto;
	left:0;
	right:0;
	top:-250px;
	bottom:0;
	line-height:40px;
	background:#6dc7d0;
	color:white;">注册</h3>
   
   
   <form action="${pageContext.request.contextPath }/user/register.action" method="post">
   
<div class="input-group-container col-lg-8" style="float:none;">
<span class="label label-warning" style="display:none;">
   <a>${msg }</a>
   <form:errors path="user.username" delimiter=", " cssStyle="color:black"/>
   <form:errors path="user.password" delimiter=", " cssStyle="color:black"/>
   </span>
 <div>
  <div class="input-group"><span id="addon1" class="input-group-addon">用户名 </span>
    <input type="text" class="form-control" placeholder="username" aria-describedby="addon1" name="username">
  </div>
  <div class="input-group"> <span id="addon2" class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
    <input type="password" class="form-control" placeholder="password" aria-describedby="addon2" name="password">
  </div>
  <div>
    <button type="submit" class="btn btn-success" >注册</button>
  </div>
  </div>
</div>
</form>
   
   
   
  </body>
  <script type="text/javascript">
  $(function(){
  var aa = $(".label a").text();
  if(aa==""&&$(".label span").length==0){
  }else{
  showMessage();    
  }
  })
  
  function showMessage(){
  $(".label").show();
  }
  </script>
  
</html>
