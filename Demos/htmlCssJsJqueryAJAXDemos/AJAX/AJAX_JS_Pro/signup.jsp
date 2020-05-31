
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>sigup</title>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/userAction_signup.action" method="post">
		<input  type="text" name="user.username" id="username" onchange="validate()"> <span id="msg">${msg }</span><br/> 
		<input type="password" name="user.password" id="password"> <br/>
		<input type="submit" value="signup">
	</form>
</body>

<script type="text/javascript">


function validate(){
	
	var xmlHttpRequest;
	if(window.ActiveXObject){
		xmlHttpRequest = new ActiveXObject("microsoft.xmlhttp");
	}else{
		xmlHttpRequest  = new XMLHttpRequest();
	}
	
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	
	var url = "userAction_findUserByName?t="+new Date().getTime();
	xmlHttpRequest.open("post", url ,true);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("user.username="+username);

	xmlHttpRequest.onreadystatechange = function (){
		if(xmlHttpRequest.readyState==4){
			if(xmlHttpRequest.status==200){
				var msg = xmlHttpRequest.responseText;
				document.getElementById("msg").innerHTML = msg;
			}
		}
	}
}




</script>
</html>
