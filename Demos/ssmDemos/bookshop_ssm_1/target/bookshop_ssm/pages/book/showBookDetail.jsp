<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
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
                    href="${pageContext.request.contextPath }/book/initModifyBook.action?id=${book.id}"
                    title="Link">修改图书信息</a>
            <a
                    href="${pageContext.request.contextPath }/book/removeBook.action?id=${book.id}"
                    title="Link">删除图书</a>
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
                        alt="No Cover"/>
            </div>
            <pre id="bookContent">no content </pre> <!-- The author information is contained here -->
            <aside id="authorInfo">
                <h2>${book.author.name}</h2>
                <p>pp</p>
            </aside>
        </section>
        <section id="sidebar"> <!-- sideBar --> <input type="text"
                                                       placeholder="Search">
            <div id="adimage">
                <img src="images/300x250.gif" alt=""/>
            </div>
            <nav>
                <ul id="orders" style="font-size:12px;">
                    <%--	<li><a
                            href="${pageContext.request.contextPath }/order/acceptOrder.action?borrower.id=${order.borrower.id}&book.id=${book.id}">${order.borrower.username }</a>&nbsp;&nbsp;&nbsp;<a>「${order.exchangeBook.name }」</a></li>
                --%></ul>
            </nav>
        </section>

        <footer> <!-- footer -->
            <article><h3>Footer1</h3>
                <p>p1</p></article>
            <article><h3>Footer2</h3>
                <p>p2</p></article>
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
        showMessage();
        showContent();
        getOrdersData();
    });

    function showMessage() {
        if ("${param.msg}" !== "") {
            alert("${param.msg}");
        }
    }

    function goToTop() {
        document.getElementById("mainwrapper").scrollIntoView(true);
    }

    function showContent() {
        var url = "${pageContext.request.contextPath}" + "/" + "${book.bookUri==null?'null':book.bookUri}";
        $.get(url, function (data) {
            $("#bookContent").html(data);
        })
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
        $("#orders").prepend($("<a>" + finalStatus + "</a>"));
    }

    function getOrdersData() {
        var url = "${pageContext.request.contextPath}/order/getOrderByBook.action";
        var params = {
            id: "${book.id}"
        };
        $.post(url, params, function (data) {
            showAllOrderInfo(data, "orders");
            showBookStatus(data);
            bindOrderHover();
        });
    }

    function showAllOrderInfo(ordersArray, containerId) {
        for (var i = 0; i < ordersArray.length; i++) {
            var order = ordersArray[i];
            var id = order.id;
            var bookId = order.book.id;
            var exchangerId = order.exchanger.id;
            var exchangerName = order.exchanger.username;
            var exchangeBookId = order.exchangeBook.id;
            var exchangeBookName = order.exchangeBook.name;

            var orderLi = $("<li><a href=\"javascript:acceptOrder(\'" + id + "\',\'" + bookId + "\')\">" + exchangerName + "</a>&nbsp;&nbsp;&nbsp;<a>「" + exchangeBookName + "」</a></li>");
            $("#" + containerId).append(orderLi);
        }
    }

    function acceptOrder(id, bookId) {
        var url = "${pageContext.request.contextPath }/order/acceptOrder.action?id=" + id + "&book.id=" + bookId;
        $.get(url, function (data) {
            if (data === "success") {
                alert(data);
                /* $("nav ul *").remove();
                 getOrdersData();*/
            } else {
                alert(data)
            }
        });
    }

    function bindOrderHover() {
        var temp = "";
        var aFirstTag = $("nav ul li a:first-child");
        aFirstTag.hover(function () {
            temp = aFirstTag.text();
            aFirstTag.text("与ta交换书籍");
        }, function () {
            aFirstTag.text(temp);
        });
    }
</script>
</html>
