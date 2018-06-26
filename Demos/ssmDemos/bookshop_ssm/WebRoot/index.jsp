<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div style="text-align:right;">
			<a href="${pageContext.request.contextPath}/user/logout.action">登出</a>
		</div>
		<header class="header">
		<h4 class="logo">
			Book <br>
		</h4>
		</header>
		<!-- Hero Section -->
		<section class="intro">
		<div class="column">
			<h3>
				<a id="username"></a>
			</h3>
			<img src="images/profile.png" alt="" class="profile">
		</div>
		<div class="column">
			<p>descriptionOfMyself</p>
		</div>
		</section>

		<div class="headButtons">
			<a href="${pageContext.request.contextPath }/book/initAddBook.action"><div
					class="button"
					style="width:100px;height: 50px;display: flex; justify-content: center;align-items: center;flex-flow: row wrap;">
					添加书籍</div></a> <a
				href="${pageContext.request.contextPath }/book/showAllBooks.action"><div
					class="button"
					style="width:100px;height: 50px;display: flex; justify-content: center;align-items: center;flex-flow: row wrap;">
					所有书籍</div></a>
		</div>
		<div class="gallery">
			<br> <span>我的图书</span>
			<hr>
			<!-- Stats Gallery Section -->
			<div id="ownBookGallery" class="gallery">
				<!-- <div class="thumbnail">
					<a href="#"><img src="images/bkg_06.jpg" alt="" width="2000"
						class="cards" /></a>
					<h4>
						<a href="detail.jsp" style="text-decoration: none;color: #52BAD5">BOOKTITLE</a>
					</h4>
					<p class="tag">AUTHOR</p>
					<p class="text_column">Description</p>
				</div> -->
			</div>
			<span>借的图书</span>
			<hr>
			<div id="borrowBookGallery" class="gallery"></div>
			<span>关注的书</span>
			<hr>
			<div id="orderBookGallery" class="gallery"></div>


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
	$(function() {
		showUserDetail();
	})

	function showPermissionStatus(ordersArray) {
		for (var i = 0; i < ordersArray.length; i++) {
			var ownerPermissionStatus = ordersArray[i].ownerPermissionStatus;
			var ownerConfirmStatus = ordersArray[i].ownerConfirmStatus;
			var borrowerConfirmStatus = ordersArray[i].borrowerConfirmStatus;
			var bookId = ordersArray[i].book.id;
			var exchangeBookId = ordersArray[i].exchangeBook.id;
			if (ownerPermissionStatus == 1) {
				var orderBook = $("[data_id=" + bookId + "]");
				var exchangeBook = $("[data_id=" + exchangeBookId + "]");
				var randomColor = getRandomColor();
				orderBook.css("border-bottom", "5px solid " + randomColor);
				exchangeBook.css("border-bottom", "5px solid " + randomColor);
				/* 				orderBook.css("border-bottom", "5px solid #90EE90");
								exchangeBook.css("border-bottom", "5px solid #90EE90"); */
				var acceptOrdersBookDiv = $("#orderBookGallery .thumbnail[data_id=" + bookId + "]");
				acceptOrdersBookDiv.append("<a class='button' href='${pageContext.request.contextPath}/order/confirmOrderBookRecive.action?bookId=" + bookId + "'>确认收到</a>");
				var acceptOrdersExchangeBookDiv = $("#orderBookGallery .thumbnail[data_id=" + exchangeBookId + "]");
				acceptOrdersExchangeBookDiv.append("<a class='button' href='${pageContext.request.contextPath}/order/confirmOrderBookRecive.action?bookId=" + exchangeBookId + "'>确认收到</a>");
			}
		}
	}

	function getRandomColor() {
		var color = "#";
		for (var i = 0; i < 6; i++) {
			color += (Math.random() * 16 | 0).toString(16);
		}
		return color;
	}

	function showUserDetail() {
		var userId = getLoginUserId();
		if (userId == null) {
			window.location = "${pageContext.request.contextPath}/user/index.action";
			return;
		}
		var url = "${pageContext.request.contextPath}/book/getUserDetail.action?";
		var params = {
			id : userId
		};
		$.post(url, params, function(data) {
			//user
			var username = data.username;
			$("#username").html(username);

			//ownBooks
			var ownBooksArray = data.ownBooks;
			showBooks(ownBooksArray, "ownBookGallery");

			//borrowBooks
			var borrowBooksArray = data.borrowBooks;
			showBooks(borrowBooksArray, "borrowBookGallery");

			//orderBooks
			var ordersArray = data.orders;
			var orderBooksArray = [];
			for (var i = 0; i < ordersArray.length; i++) {
				if (ordersArray[i].borrower.id == userId) { //如果自己是借的人就顯示
					var borrowerConfirmStatus = ordersArray[i].borrowerConfirmStatus;
					if (borrowerConfirmStatus != 1) {
						orderBooksArray[i] = ordersArray[i].book;
					}
				}
			}
			if (orderBooksArray.length != 0) {
				showBooks(orderBooksArray, "orderBookGallery");
			}

			//orderExchangeBooks
			var orderExchangeBooks = []
			for (var i = 0; i < ordersArray.length; i++) {
				if (ordersArray[i].borrower.id != userId) { //如果自己不是借的人(即自己是擁有者)就顯示對方要交換的書
					var ownerConfirmStatus = ordersArray[i].ownerConfirmStatus;
					if (ownerConfirmStatus != 1) {
						orderExchangeBooks[i] = ordersArray[i].exchangeBook;
					}
				}
			}
			if (orderExchangeBooks.length != 0) {
				showBooks(orderExchangeBooks, "orderBookGallery");
			}

			showPermissionStatus(ordersArray);
		})
	}


	function showBooks(booksArray, containerId) {
		for (var i = 0; i < booksArray.length; i++) {
			if (booksArray[i] == null) {
				continue;
			}
			var bookId = booksArray[i].id;
			var bookName = booksArray[i].name;
			var bookPrice = booksArray[i].price;
			var coverUri = "";
			var bookAuthorName = null;
			var bookAuthorId = null;
			if (booksArray[i].coverUri != null) {
				coverUri = "${pageContext.request.contextPath}/" + booksArray[i].coverUri;
			}
			if (booksArray[i].author != null) {
				bookAuthorId = booksArray[i].author.id;
				bookAuthorName = booksArray[i].author.name;
			}
			var bookView = $(" <div data_id='" + bookId + "' class='thumbnail'> <a href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=" + bookId + "'><div class='cardDiv'><img src='" + coverUri + "' alt='" + bookName + "' width='2000' class='cards'/></div></a><h4><a href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=" + bookId + "' style='text-decoration: none;color: #52BAD5'>" + bookName + "</a></h4><p class='tag'><a href='${pageContext.request.contextPath}/book/showBookByAuthorId.action?id=" + bookAuthorId + "'>" + bookAuthorName + "</a></p><p class='text_column'>$" + bookPrice + "</p><p class='text_column'>Description</p></div>");
			$("#" + containerId).append(bookView);


		}

	}

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
