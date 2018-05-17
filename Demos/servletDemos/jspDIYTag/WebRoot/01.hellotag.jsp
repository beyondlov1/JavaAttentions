<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://gz.itcast.cn" prefix="itcast"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>第一个自定义标签</title>  
  </head>
  
  <body>
    <%
    	//获取当前客户的IP地址
    	//String ip = request.getRemoteHost();
    	//输出到浏览器
    	//out.write("当前客户的IP地址是："+ip);
     %>
     <%--使用标签库中的标签--%>
     <itcast:showIp>xxxxx</itcast:showIp>
     
  </body>
</html>
