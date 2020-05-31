<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showAllUser.jsp' starting page</title>
    
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
  <table>
  <c:forEach items="${user }" var="eachUser">
  
  <tr>
  <td>
    ${eachUser.id }
  </td>
  <td>
  ${eachUser.username }
  </td>
  <td>
    ${eachUser.password }
  </td>
  <td>
    <a href="DeleteServlet?id=${eachUser.id }">delete</a>
  </td>
  <td>
  <a href="ShowUserServlet?id=${eachUser.id }">update</a>
  </td>
  
  </tr>
  
  </c:forEach>
  
  </table>
  
  <form action="InsertServlet" method="post">
    <input name="username" type="text">
    <input name="password" type="password">
    <input type="submit" value="submit">
    </form>
  </body>
</html>
