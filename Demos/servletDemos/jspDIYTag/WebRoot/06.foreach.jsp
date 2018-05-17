<%@ page language="java" import="java.util.*,gz.itcast.b_cases.*" pageEncoding="utf-8"%>
<%@taglib uri="http://gz.itcast.cn" prefix="itcast" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>forEach标签</title>  
  </head>
  
  <body>
    <%
       //保存数据
       //List
     	List<Student>  list = new ArrayList<Student>();
     	list.add(new Student("rose",18));
     	list.add(new Student("jack",28));
     	list.add(new Student("lucy",38));
     	//放入域中
     	pageContext.setAttribute("list",list);
     	
     	//Map
     	Map<String,Student> map = new HashMap<String,Student>();
     	map.put("100",new Student("mark",20));
     	map.put("101",new Student("maxwell",30));
     	map.put("102",new Student("narci",40));
     	//放入域中
     	pageContext.setAttribute("map",map);
     %>
     
     <itcast:forEach items="${list}" var="student">
     		姓名：${student.name } - 年龄:${student.age }<br/>
     </itcast:forEach>
     
     <hr/>
     
     <itcast:forEach items="${map}" var="entry">
     	  编号：${entry.key} - 姓名：${entry.value.name} - 年龄：${entry.value.age }<br/>
     </itcast:forEach>
  </body>
</html>
