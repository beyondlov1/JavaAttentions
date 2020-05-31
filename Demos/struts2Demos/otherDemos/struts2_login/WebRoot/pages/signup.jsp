<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>

  </head>
  
  <body>
  <s:actionerror/>
  <form action="${pageContext.request.contextPath }/user/signup.action" method="post">
	  <span>用户名:</span><input id="username" name="user.username" type="text" onkeyup="vertifyUsername()"><span id="message"></span><s:fielderror ><s:param>user.username</s:param></s:fielderror><br>
	  <span>密码:</span><input name="user.password" type="password"><s:fielderror ><s:param>user.password</s:param></s:fielderror><br>
	  <span>确认密码:</span><input name="repassword" type="password"><s:fielderror ><s:param>repassword</s:param></s:fielderror><br>
	  <input type="submit" value="注册"><span>${msg }</span>
  </form>
  </body>
  <script type="text/javascript">
  var xmlHttpRequest = new XMLHttpRequest();
  xmlHttpRequest.onreadystatechange=function(){
  if(xmlHttpRequest.readyState==4&&xmlHttpRequest.status==200){
  document.getElementById("message").innerHTML = xmlHttpRequest.responseText;
  }
  }
  
  function vertifyUsername(){
  var username = document.getElementById("username").value;
  xmlHttpRequest.open("GET", "${pageContext.request.contextPath }/user/isUserExist.action?user.username="+username);
  xmlHttpRequest.send();
  }
  
  
  </script>
  <style type="text/css">
ul {
	margin: 0;
	display: inline-block;
	padding: 0;
	list-style-type: none;
}
</style>
</html>
