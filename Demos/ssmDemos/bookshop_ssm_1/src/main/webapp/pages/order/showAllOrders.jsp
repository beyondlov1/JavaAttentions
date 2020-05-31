<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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

    <div class="gallery">
        <span>待确认订单</span>
        <hr>
        <div id="toAcceptOrdersGallery" class="gallery"></div>

        <span>待收货的订单</span>
        <hr>
        <div id="toConfirmOrdersGallery" class="gallery"></div>

        <span>待换出的订单</span>
        <hr>
        <div id="toExchangeOrdersGallery" class="gallery"></div>

        <span>已完成的订单</span>
        <hr>
        <div id="completedOrdersGallery" class="gallery"></div>


        <%--<span>未確認</span>
        <hr>
        <div id="toAcceptOrdersGallery" class="gallery"></div>

        <span>对方未接受的订单</span>
        <hr>
        <div id="notAcceptedOrdersGallery" class="gallery"></div>

        <span>对方已接受的订单</span>
        <hr>
        <div id="acceptedOrdersGallery" class="gallery"></div>

        <span>未借出书籍</span>
        <hr>
        <div id="exchangeNotOutOrdersGallery" class="gallery"></div>

        <span>已借出书籍</span>
        <hr>
        <div id="exchangeOutOrdersGallery" class="gallery"></div>

        <span>未获得的换取书籍</span>
        <hr>
        <div id="exchangeNotInOrdersGallery" class="gallery"></div>

        <span>已获得的换取书籍</span>
        <hr>
        <div id="exchangeInOrdersGallery" class="gallery"></div>

        <span>已完成</span>
        <hr>
        <div id="completedOrdersGallery" class="gallery"></div>--%>

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
</div>
<!-- Main Container Ends -->
</body>

<script type="text/javascript">
    $(function () {
        showOrders();
    })

    function showOrders() {
        var url = "${pageContext.request.contextPath}/order/getRelatedOrdersByUser.action";
        var userId = getLoginUserId();
        if (userId == null) {
            window.location = "${pageContext.request.contextPath}/user/index.action";
            return;
        }
        var params = {
            id: userId
        };
        $.post(url, params, function (data) {
            for (var i = 0; i < data.length; i++) {
                var order = data[i];
                showOrder(order)
            }
        })
    }

    function showOrder(order) {
        var orderBook = order.book;
        var orderExchangeBook = order.exchangeBook;
        var orderStatus = order.status;
        var orderOwnerId = order.owner.id;
        var orderExchangerId = order.exchanger.id;
        var loginUserId = getLoginUserId();

        if (orderExchangerId === loginUserId && orderStatus === 0) {//getNotAcceptedOrdersUrl
            var bookView = createBookView(orderBook);
            $("#notAcceptedOrdersGallery").append(bookView)
        }
        if (orderExchangerId === loginUserId && orderStatus === 1) {//getAcceptedOrdersUrl
            var bookView = createBookView(orderBook);
            var confirmUrl = "${pageContext.request.contextPath}/order/exchangerConfirm.action?id=" + order.id;
            var buttonView = createButton(confirmUrl);
            bookView.append(buttonView);
            $("#toConfirmOrdersGallery").append(bookView);
        }
        if (orderOwnerId === loginUserId && orderStatus === 0) {//getToAcceptOrders
            var bookView = createBookView(orderBook);
            $("#toAcceptOrdersGallery").append(bookView);
        }
        if (orderExchangerId === loginUserId && (orderStatus === 1 || orderStatus === 5)) {//getExchangeNotOutOrdersUrl
            var bookView = createBookView(orderExchangeBook);
            $("#toExchangeOrdersGallery").append(bookView);
        }
        if (orderOwnerId === loginUserId && (orderStatus === 1 || orderStatus === 3)) {//未发出的订单书籍
            var bookView = createBookView(orderBook);
            $("#toExchangeOrdersGallery").append(bookView);
        }
        if (orderExchangerId === loginUserId && orderStatus === 3) {//getExchangeOutOrdersUrl
            var bookView = createBookView(orderExchangeBook);
            $("#exchangeOutOrdersGallery").append(bookView);
        }
        if (orderOwnerId === loginUserId && (orderStatus === 1 || orderStatus === 5)) {//getExchangeNotInOrdersUrl
            var bookView = createBookView(orderExchangeBook);
            var confirmUrl = "${pageContext.request.contextPath}/order/ownerConfirm.action?id=" + order.id;
            var buttonView = createButton(confirmUrl);
            bookView.append(buttonView);
            $("#toConfirmOrdersGallery").append(bookView);
        }
        if (orderOwnerId === loginUserId && orderStatus === 5) {//getExchangeInOrdersUrl
            var bookView = createBookView(orderExchangeBook);
            $("#exchangeInOrdersGallery").append(bookView);
        }
        if ((orderOwnerId === loginUserId || orderExchangerId === loginUserId) && orderStatus === 7) {//getCompletedOrdersUrl
            var bookView = createBookView(orderBook);
            var bookView1 = createBookView(orderExchangeBook);
            $("#completedOrdersGallery").append(bookView).append(bookView1);
        }
    }

    function createBookView(book) {
        if (book == null) {
            return;
        }
        var bookId = book.id;
        var bookName = book.name;
        var bookPrice = book.price;
        var coverUri = "";
        var bookAuthorName = null;
        var bookAuthorId = null;
        if (book.coverUri != null) {
            coverUri = "${pageContext.request.contextPath}/" + book.coverUri;
        }
        if (book.author != null) {
            bookAuthorId = book.author.id;
            bookAuthorName = book.author.name;
        }
        return $(" <div data_id='" + bookId + "' class='thumbnail'> <a href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=" + bookId + "'><div class='cardDiv'><img src='" + coverUri + "' alt='" + bookName + "' width='2000' class='cards'/></div></a><h4><a href='${pageContext.request.contextPath}/book/showBookDetailById.action?id=" + bookId + "' style='text-decoration: none;color: #52BAD5'>" + bookName + "</a></h4><p class='tag'><a href='${pageContext.request.contextPath}/book/showBookByAuthorId.action?id=" + bookAuthorId + "'>" + bookAuthorName + "</a></p><p class='text_column'>$" + bookPrice + "</p><p class='text_column'>Description</p></div>");
    }

    function createButton(confirmUrl) {
        return $("<a class='button' href=\"" + confirmUrl + "\">确认收到</a>");
    }

    function getLoginUserId() {
        var userId = null;
        var myCookie = document.cookie;
        var cookieArray = myCookie.split("; ");
        for (var i = 0; i < cookieArray.length; i++) {
            var cookieName = cookieArray[i].split("=")[0];
            if (cookieName === "loginUserId") {
                userId = cookieArray[i].split("=")[1];
            }
        }
        return userId;
    }
</script>
</html>
