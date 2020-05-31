<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/simpleGridTemplate.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>

<body>

	<div class="container">
		<!-- Header -->
		<div style="text-align:left;">
			<a href="${pageContext.request.contextPath}/user/index.action">我的</a>
		</div>
		<header class="header">
		<h4 class="logo">
			Book<br>
		</h4>
		</header>


		<div class="gallery">
			<div id="ownBookGallery" class="gallery">
				<c:forEach items="${books }" var="book">
					<div class='thumbnail'>
						<a
							href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=${book.id }'>
							<div
								class='cardDiv'>
								<img src='${book.coverUri }' alt='${book.name }' width='2000'
									class='cards' />
							</div></a>
						<h4>
							<a
								href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=${book.id }'
								style='text-decoration: none;color: #52BAD5'>${book.name }</a>
						</h4>
						<p class='tag'>
							<a
								href='${pageContext.request.contextPath}/book/showBookByAuthorId.action?id=${book.author.id }'>${book.author.name }</a>
						</p>
						<p class='text_column'>$${book.price }</p>
						<p class='text_column'>Description</p>
					</div>
				</c:forEach>
			</div>
			<!-- Footer Section -->
			<footer id="contact">
			<p class="hero_header">GET IN TOUCH WITH ME</p>
			<div class="button">EMAIL ME</div>
			</footer>
			<!-- Copyrights Section -->
			<div class="copyright">
				&copy;2015 - <strong>GRID</strong>
			</div>
		</div>
		<!-- Main Container Ends -->
</body>

<script type="text/javascript">

	function getLoginUserId() {
		var userId = null;
		var myCookie = document.cookie;
		var cookieArray = myCookie.split("; ");
		for (var i = 0; i < cookieArray.length; i++) {
			var cookieName = cookieArray[i].split("=")[0];
			if (cookieName == "loginUserId") {
				userId = cookieArray[i].split("=")[1];
			}
		}
		return userId;
	}
</script>
</html>
