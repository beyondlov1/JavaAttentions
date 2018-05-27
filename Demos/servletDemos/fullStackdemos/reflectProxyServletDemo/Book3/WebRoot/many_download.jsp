<%@ page language="java" import="java.util.*,java.io.*,java.net.*, java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'many_download.jsp' starting page</title>
    
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
  
	<%
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
	String root = this.getServletContext().getRealPath("/") + "uploadFiles" + File.separator
					+ format.format(Calendar.getInstance().getTime());
		root = this.getServletContext().getRealPath("/") ;
	    String rootPath  = (String)request.getAttribute("filePath");
		//String rootPath = request.getServletContext().getRealPath("/") + "/uploadFiles/2018_05_10";
		Queue<File> queue = new LinkedList<File>();
		queue.offer(new File(rootPath));
		System.out.println(rootPath+"   "+root);
		if(!(rootPath+File.separator).trim().equals(root.trim())&&rootPath.trim().length()>root.trim().length()){//upload目录以下都可访问,以上不可访问
		if(!rootPath.trim().equals((root+"uploadFiles").trim())){//目录到达uploadFiles时不显示上一层
		%>
		<a href="${pageContext.request.contextPath }/TestDownload?filePath=<%=URLEncoder.encode(new File(rootPath).getParentFile().getPath(),"UTF-8")%>">返回上一层</a><br>
		<%
		}
		
		while (!queue.isEmpty()) {
			File file = queue.poll();
			File[] list = file.listFiles();
			for (File f : list) {
				if (f.isDirectory()) {
				%>
				<a href="${pageContext.request.contextPath }/TestDownload?filePath=<%=URLEncoder.encode(f.getPath(),"UTF-8")%>"><%=f.getName().toUpperCase()%></a><br>
				<%
				} else {
	%>
	<a href="${pageContext.request.contextPath }/TestDownload?filePath=<%=URLEncoder.encode(f.getPath(),"UTF-8") %>"><%=f.getName()%></a><br>
	<%
		        }
			}
		}
		}else{
		%>
		<a href="${pageContext.request.contextPath }/TestDownload">返回根目录</a>
		<%
		}
	%>
</body>
</html>
