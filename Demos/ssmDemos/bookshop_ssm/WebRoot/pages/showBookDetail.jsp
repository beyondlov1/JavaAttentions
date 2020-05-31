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

<title>My JSP 'showBookDetail.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link href="css/blogPostStyle.css" rel="stylesheet" type="text/css">
<!--The following script tag downloads a font from the Adobe Edge Web Fonts server for use within the web page. We recommend that you do not modify it.-->
<script>var __adobewebfontsappname__ = "dreamweaver"
</script>
<script
	src="http://use.edgefonts.net/montserrat:n4:default;source-sans-pro:n2:default.js"
	type="text/javascript"></script>

</head>

<body>
	<div id="mainwrapper">
		<header> <!--header-->
		<div id="logo">
			<!-- <img src="logoImage.png" alt="sample logo"> -->
			<!-- Company Logo text -->
			Logo
		</div>
		<div id="nav">
			<a
				href="${pageContext.request.contextPath }/book/initModifyBook.action?id=${book.id}"
				title="Link">修改图书信息</a> <a
				href="${pageContext.request.contextPath }/book/removeBook.action?id=${book.id}"
				title="Link">删除图书</a><a href="#" title="Link">Nav3</a>
		</div>
		</header>
		<div id="content">
			<div class="notOnDesktop">
				<!-- This search box is displayed only in mobile and tablet laouts and not in desktop layouts -->
				<input type="text" placeholder="Search">
			</div>
			<section id="mainContent"> <!-- content -->
			<h1>${book.name}</h1>
			<h3>SOME AWESOME HERO TAGLINE</h3>
			<div id="bannerImage">
				<img
					src="${pageContext.request.contextPath}/${book.coverUri==null?'null':book.coverUri}"
					alt="No Cover" />
			</div>
			<pre id="bookContent">no content </pre> <!-- The author information is contained here -->
			<aside id="authorInfo">
			<h2>${book.author.name}</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
				enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
				ut aliquip ex ea commodo consequat.</p>
			</aside> </section>
			<section id="sidebar"> <!-- sideBar --> <input type="text"
				placeholder="Search">
			<div id="adimage">
				<img src="images/300x250.gif" alt="" />
			</div>
			<nav>
			<ul style="font-size:12px;">
				<c:forEach items="${book.orders}" var="order">
					<li><a
						href="${pageContext.request.contextPath }/order/acceptOrder.action?borrower.id=${order.borrower.id}&book.id=${book.id}">${order.borrower.username }</a>&nbsp;&nbsp;&nbsp;<a>「${order.exchangeBook.name }」</a></li>
				</c:forEach>
			</ul>
			</nav> </section>
			<footer> <!-- footer --> <article>
			<h3>Footer1</h3>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
			</article> <article>
			<h3>Footer2</h3>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
			</article> </footer>
		</div>
		<div id="footerbar">
			<!-- Small footerbar at the bottom -->
		</div>
	</div>

	<div id="goToTop"
		style="width:50px;height:50px;background:#eee;text-align:center;line-height:50px;position:fixed;right:20px;bottom:20px;">
		<a style="color:black;" href="javascript:goToTop()"><div>TOP</div></a>
	</div>
</body>

<script>
	$(function() {
		showContent();
		showStatus();
		bindOrderHover();
	})

	function goToTop() {
		document.getElementById("mainwrapper").scrollIntoView(true);
	}

	function showContent() {
		var url = "${pageContext.request.contextPath}" + "/" + "${book.bookUri==null?'null':book.bookUri}";
		$.get(url, function(data) {
			$("#bookContent").html(data);
		})
	}
	function showStatus() {
		var url = "${pageContext.request.contextPath}/order/getAcceptOrdersByEitherBookId.action?id=${book.id}";
		$.get(url, function(data) {
			if (data != "") {
				var orderBorrowerNameArray = [];
				var orderExchangeBookNameArray = [];
				var orderBookNameArray = [];
				var orderBookOwnerNameArray = [];
				for (var i = 0; i < data.length; i++) {
					if (data[i].book.id != "${book.id}") {
						orderBookNameArray[i] = data[i].book.name;
						orderBookOwnerNameArray[i] = data[i].book.owner.username;
						var borrowerConfirmStatus = data[i].borrowerConfirmStatus;
						var orderBookId = data[i].book.id;
						var orderBookOwnerId = data[i].book.owner.id;


						var status = "";
						if ("${book.borrower.id}"!="") {
							status = "持有者: ";
						} else {
							status = "交換中: ";
						}
						var li = $("<li><a>" + status + "</a> <a href='${pageContext.request.contextPath}/book/showBookByOwnerId.action?id=" + orderBookOwnerId + "'>" + orderBookOwnerNameArray[i] + "</a>&nbsp;&nbsp;<a  href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=" + orderBookId + "'>「" + orderBookNameArray[i] + "」</a></li>")
						$("nav ul").prepend(li)
					} else {
						orderBorrowerNameArray[i] = data[i].borrower.username;
						orderExchangeBookNameArray[i] = data[i].exchangeBook.name;
						var ownerConfirmStatus = data[i].ownerConfirmStatus;
						var orderBookId = data[i].book.id;
						var orderBookOwnerId = data[i].book.owner.id;

						var status = "";
						if ("${book.borrower.id}"!="") {
							status = "持有者: ";
						} else {
							status = "交換中: ";
						}
						var li = $("<li><a >" + status + "</a> <a href='${pageContext.request.contextPath}/book/showBookByOwnerId.action?id=" + orderBookOwnerId + "'>" + orderBorrowerNameArray[i] + "</a>&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=" + orderBookId + "'>「" + orderExchangeBookNameArray[i] + "」</a></li>")
						$("nav ul").prepend(li)
					}

				}
			}
		});
	}

	function bindOrderHover() {
	var temp ="";
	var aFirstTag = $("nav ul li a:first-child");
		aFirstTag.hover(function() {
		temp = aFirstTag.text();
			aFirstTag.text("与ta交换书籍");
		},function(){
		aFirstTag.text(temp);
		});
	}
</script>
</html>
