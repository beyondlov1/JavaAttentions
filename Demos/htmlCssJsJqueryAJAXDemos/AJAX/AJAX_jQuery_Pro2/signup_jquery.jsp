
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>sigup_jquery</title>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath }/userAction_signup.action" method="post">
		<input  type="text" name="user.username" id="username" onkeyup="validate()"> <span id="msg">${msg }</span><br/> 
		<input type="password" name="user.password" id="password"> <br/>
		<input type="submit" value="signup">
	</form>
</body>

<script type="text/javascript">

function validate(){
	
	var username = document.getElementById("username").value;
	
	var url = "userAction_findUserByName?t="+new Date().getTime();
	
	$.post(url,{"user.username": username}, function(data){
		document.getElementById("msg").innerHTML = data;
	})
}

</script>
</html>
