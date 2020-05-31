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
				href="${pageContext.request.contextPath}/order/initAddOrder.action?bookId=${book.id}"
				title="Link">想借</a>
		</div>
		</header>
		<div id="content">
			<div class="notOnDesktop">
				<!-- This search box is displayed only in mobile and tablet laouts and not in desktop layouts -->
				<input type="text" placeholder="Search">
			</div>
			<section id="mainContent"> <!-- content -->
			<h1>
				<!-- Blog title -->${book.name}</h1>
			<h3>
				<!-- Tagline -->
				SOME AWESOME HERO TAGLINE
			</h3>
			<div id="bannerImage">
				<img
					src="${pageContext.request.contextPath}/${book.coverUri==null?'null':book.coverUri}"
					alt="No Cover" />
			</div>
			<pre id="bookContent">no content</pre> <aside id="authorInfo">
			<!-- The author information is contained here -->
			<h2>${book.author.name}</h2>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
				enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
				ut aliquip ex ea commodo consequat.</p>
			</aside> </section>
			<section id="sidebar"> <!--sidebar--> <input type="text"
				placeholder="Search">
			<div id="adimage">
				<img src="images/300x250.gif" alt="" />
			</div>
			<nav>
			<ul style="font-size:12px;">
			<a>交换者列表:</a>
				<c:forEach items="${book.orders}" var="order">
					<li><a
						href="${pageContext.request.contextPath }/book/showBookByOwnerId.action?id=${order.borrower.id}">${order.borrower.username }</a>&nbsp;&nbsp;&nbsp;<a style="font-style:italic;">「${order.exchangeBook.name }」</a></li>
				</c:forEach>
			</ul>
			</nav> </section>
			<footer> <!--footer--> <article>
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
		showFocusStatus();
		showOrderStatus();
		showContent();
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

	function showFocusStatus() {
		if ("${hasFocus}" == "true") {
			$("#nav a:eq(0)").text("已关注");
			$("#nav a:eq(0)").attr("href", null);

			$("#nav a:eq(0)").hover(function() {
				$("#nav a:eq(0)").text("取消关注");
				$("#nav a:eq(0)").attr("href", "${pageContext.request.contextPath}/order/cancelOrder.action?book.id=${book.id}");
			}, function() {
					$("#nav a:eq(0)").text("已关注");
			});
		}

		var isBorrowed = $("<a href='${pageContext.request.contextPath}/book/cancelBorrow.action?bookId=${book.id}'>已借</a> ")
		if ("${hasBorrowed}" == "true") {
			$("#nav").append(isBorrowed);
			isBorrowed.hover(function() {
				isBorrowed.text("归还");
			},
				function() {
					isBorrowed.text("已借");
				});
		}
	}
	
	function showOrderStatus() {
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
						var li = $("<li><a>"+status+"</a> <a href='${pageContext.request.contextPath}/book/showBookByOwnerId.action?id="+orderBookOwnerId+"'>" + orderBookOwnerNameArray[i] + "</a>&nbsp;&nbsp;<a  href='${pageContext.request.contextPath}/book/showBookDetailById.action?id="+orderBookId+"'>「" + orderBookNameArray[i] + "」</a></li>")
						$("nav ul").prepend(li)
					} else {
						orderBorrowerNameArray[i] = data[i].borrower.username;
						orderExchangeBookNameArray[i] = data[i].exchangeBook.name;
						var borrowerConfirmStatus = data[i].borrowerConfirmStatus;
						var orderBookId = data[i].book.id;
						var orderBookOwnerId = data[i].book.owner.id;

						var status = "";
						if ("${book.borrower.id}"!="") {
							status = "持有者: ";
						} else {
							status = "交換中: ";
							
						}
						var li = $("<li><a >"+status+"</a> <a href='${pageContext.request.contextPath}/book/showBookByOwnerId.action?id="+orderBookOwnerId+"'>" + orderBorrowerNameArray[i] + "</a>&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/book/showBookDetailById.action?id="+orderBookId+"'>「" + orderExchangeBookNameArray[i] + "」</a></li>")
						$("nav ul").prepend(li)
					}

				}
			}
			
		});
	}
</script>
</html>
