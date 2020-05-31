
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>login</title>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

</head>
<body onunload="alert('The onunload event was triggered')">

<div>
<s:debug></s:debug>
	<form action="${pageContext.request.contextPath }/userAction_login.action" method="post">
		username: <input type="text" name="user.username"> <br>
		password:<input type="password" name="user.password"> <br>
		<input type="submit" value="login">
	</form>
    <a href="${pageContext.request.contextPath }/userAction_initSignup.action">signup</a>
    <s:actionerror id="actionError"/>
    </div>
</body>

<script type="text/javascript">
$("#actionError").hide();
$("#actionError").slideDown(200)

</script>
</html>
