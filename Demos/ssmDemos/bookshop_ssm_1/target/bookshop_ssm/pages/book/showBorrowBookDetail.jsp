<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    href="${pageContext.request.contextPath}/order/initAddOrder.action?id=${book.id}"
                    title="Link">想借</a>
        </div>
    </header>
    <div id="content">
        <div class="notOnDesktop">
            <input type="text" placeholder="Search">
        </div>
        <section id="mainContent"> <!-- content -->
            <h1>
                <!-- Blog title -->${book.name}</h1>
            <h3>
                SOME AWESOME HERO TAGLINE
            </h3>
            <div id="bannerImage">
                <img
                        src="${pageContext.request.contextPath}/${book.coverUri==null?'null':book.coverUri}"
                        alt="No Cover"/>
            </div>
            <pre id="bookContent">no content</pre>
            <aside id="authorInfo">
                <!-- The author information is contained here -->
                <h2>${book.author.name}</h2>
                <p>pp</p>
            </aside>
        </section>
        <section id="sidebar"> <!--sidebar--> <input type="text"
                                                     placeholder="Search">
            <div id="adimage">
                <img src="images/300x250.gif" alt=""/>
            </div>
            <nav>
                <ul style="font-size:12px;">
                    <a>交换者列表:</a>
                    <%--	<li><a
                            href="${pageContext.request.contextPath }/book/showBookByOwnerId.action?id=${order.borrower.id}">${order.borrower.username }</a>&nbsp;&nbsp;&nbsp;<a style="font-style:italic;">「${order.exchangeBook.name }」</a></li>
                --%></ul>
            </nav>
        </section>
        <footer> <!--footer-->
            <article>
                <h3>Footer1</h3>
                <p>p1</p>
            </article>
            <article>
                <h3>Footer2</h3>
                <p>p2</p>
            </article>
        </footer>
    </div>
    <div id="footerbar">
        <!-- Small footerbar at the bottom -->
    </div>
</div>

<div id="goToTop"
     style="width:50px;height:50px;background:#eee;text-align:center;line-height:50px;position:fixed;right:20px;bottom:20px;">
    <a style="color:black;" href="javascript:goToTop()">
        <div>TOP</div>
    </a>
</div>
</body>

<script>
    $(function () {
        showFocusStatus();
        showStatus();
        showContent();
    })

    function goToTop() {
        document.getElementById("mainwrapper").scrollIntoView(true);
    }

    function showContent() {
        var url = "${pageContext.request.contextPath}" + "/" + "${book.bookUri==null?'null':book.bookUri}";
        $.get(url, function (data) {
            $("#bookContent").html(data);
        })
    }

    function showFocusStatus(order) {
        var isFocus = false;
        if (order !== "" && order != null) {
            isFocus = true;
        }
        if (isFocus === true) {
            var aa = $("#nav a:first").text("已关注");
            if (order.status === 0) {
                aa.hover(function () {
                    aa.text("取消关注").attr("href", "${pageContext.request.contextPath}/order/removeOrder.action?id=" + order.id);
                }, function () {
                    aa.text("已关注").attr("href", null);
                })
            } else if (order.status === 5 || order.status === 7) {
                aa.text("已借").attr("href", null);
                aa.hover(function () {
                    aa.text("归还").attr("href", "${pageContext.request.contextPath}/order/########.action?id=" + order.id);
                }, function () {
                    aa.text("已借").attr("href", null);
                })
            }
        }
    }

    function showOrders(data) {
        for (var i = 0; i < data.length; i++) {
            var order = data[i];
            var orderLi = $("<li><a href=\"${pageContext.request.contextPath }/book/showBookByOwnerId.action?id=" + order.exchanger.id + "\">" + order.exchanger.username + "</a>&nbsp;&nbsp;&nbsp;<a style=\"font-style:italic;\">「" + order.exchangeBook.name + "」</a></li>");
            $("nav ul").append(orderLi);
        }
    }

    function showBookStatus(data) {
        var finalStatus = "";
        for (var i = 0; i < data.length; i++) {
            var status = data[i].status;
            if (status === 1) {
                finalStatus = "正在与" + data[i].exchanger.username + "交易中,交易图书为" + data[i].exchangeBook.name;
            } else if (status === 3) {
                finalStatus = "正在与" + data[i].exchanger.username + "交易中," + data[i].exchangeBook.name + "已收到";
            } else if (status === 5) {
                finalStatus = data[i].exchanger.username + "已收到图书"
            } else if (status === 7) {
                finalStatus = "交易完成, 本书持有者:" + data[i].exchanger.username;
            }
        }
        $("nav ul").prepend($("<a>" + finalStatus + "</a><br><br>"));
    }

    function showStatus() {
        var url = "${pageContext.request.contextPath}/order/getAcceptOrdersByEitherBookId.action?id=${book.id}";
        $.get(url, function (data) {
            showFocusStatus(data);
        });
        var url1 = "${pageContext.request.contextPath}/order/getOrderByBook.action?id=${book.id}";
        $.get(url1, function (data) {
            showBookStatus(data);
            showOrders(data);
        });
    }
</script>
</html>
