
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>sigup_jquery</title>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>

<s:debug></s:debug>
	
	<div>
	<form action="${pageContext.request.contextPath }/userAction_signup.action" method="post">
		username:<input  type="text" name="user.username" id="username" onkeyup="validate()"> <span id="msg"></span><br/> 
		password:<input type="password" name="user.password" id="password"> <br/>
		repassword:<input type="password" name="repassword" id="repassword"> <br/>
		<input id="submit" type="submit" value="signup">
	</form>
	</div>
	
	<s:fielderror></s:fielderror>
</body>

<script type="text/javascript">



function validate(){
	var username = document.getElementById("username").value;
	
	if(username.length<6){
		$("#msg").text(null);
		return;
	}	
	
	var url = "userAction_ajaxVertifyUser?t="+new Date().getTime();
	
	$.post(url,{"user.username": username}, function(data){
		
		document.getElementById("msg").innerHTML = data;
		
		if(data=="username avaliable"){
			$("#submit").prop("disabled", false);
		}else{
			$("#submit").prop("disabled", true);
		}
		
	})
}

</script>
</html>
