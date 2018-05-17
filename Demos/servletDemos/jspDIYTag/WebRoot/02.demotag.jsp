<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://gz.itcast.cn" prefix="itcast"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>第二个自定义标签</title>  
  </head>
  
  <body>
    <itcast:demoTag num="2">xxxx${10+5}</itcast:demoTag>
          标签余下内容
  </body>
</html>
