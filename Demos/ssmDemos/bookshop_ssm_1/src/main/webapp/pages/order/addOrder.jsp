<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
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

<title>My JSP 'addBook.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>

<body>
	This is my JSP page.
	<br> ${msg}
	<form
		action="${pageContext.request.contextPath }/order/addOrder.action" method="post">
		<input style="display:none" name="book.id" value="${book.id}">
		<input style="display:none" name="exchanger.id">

		<select id="bookSelect" name="exchangeBook.id">
		</select>
		<input type="submit" value="提交">
	</form>
</body>


<script type="text/javascript">
    $(function () {
        initForm();
    });

    function initForm() {
        //init loginUserId
        $(":input[name='exchanger.id']").val(getLoginUserId());

        //init select
       var url = "${pageContext.request.contextPath }/book/getAvailableBookByOwner.action";
       var params = {
           id:getLoginUserId()
       };
       $.post(url,params,function (data) {
           var ownBooksArray = data;
           for (var i = 0; i <ownBooksArray.length; i++) {
               var bookId = ownBooksArray[i].id;
               var bookName = ownBooksArray[i].name;
               var option = $("<option></option>");
               option.val(bookId);
               option.text(bookName);
               $("#bookSelect").append(option);
           }
       });
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
