<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'signup.sp' starting page</title>

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

	<form action="${pageContext.request.contextPath }/UserServlet?op=signup" method="post">
		<span>用户名:</span><input id="username"  name="username" type="text" onkeyup="isUserExist()"><span id="msg"></span><br>
		<span>密码:</span><input name="password" type="password"><br>
		<input type="submit" value="注册"><span>${msg }</span>
	</form>
	
</body>

<script type="text/javascript" >

var xmlRequest = new XMLHttpRequest();

xmlRequest.onreadystatechange = function(){
if(xmlRequest.readyState == 4 && xmlRequest.status == 200){
var result = xmlRequest.responseText;
document.getElementById("msg").innerHTML = result;
}
}

function isUserExist(){
//获取输入
var username = document.getElementById("username").value;
var context = "${pageContext.request.contextPath}";

if(username.trim().length>5){
//ajax 查询有无账户
xmlRequest.open("POST",context+"/UserServlet?op=isUserExist",true);
xmlRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");//post一定要加
xmlRequest.send("username="+username);
}else{
document.getElementById("msg").innerHTML = null;
}
}

</script>
</html>
