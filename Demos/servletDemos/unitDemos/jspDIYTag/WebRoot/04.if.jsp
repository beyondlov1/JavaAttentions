<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://gz.itcast.cn" prefix="itcast" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>if标签</title>  
  </head>
  
  <body>
    <itcast:if test="${10>5}">
    	条件成立
    </itcast:if>
  </body>
</html>
