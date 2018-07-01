<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

    <style type="text/css">
        form{
            display: flex;
            position:absolute;
            margin: auto;
            width: 300px;
            height: 200px;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            flex-flow: column nowrap;
            justify-content: space-around;
            align-items: start;
            background: #eeeeee;
        }

        form div{
            display: flex;
            flex-flow: row nowrap;
            justify-content: space-around;
            width: 100%;
        }
        
        form div:first-child{
            background: #6DC7D0;
            margin-top: 3px;
            margin-bottom: 3px;
            color: white;
        }
        input{
            width: 80%;
        }
        input[type='submit']{
            width: 100%;
        }

    </style>
</head>


<body>
${msg}
<form action="${pageContext.request.contextPath }/book/addBook.action" method="post" enctype="multipart/form-data">
    <div><h3>添加图书</h3></div>
    <input type="text" name="owner.id" style="display:none">
    <div>
    名称:<input type="text" name="name">
    </div>
    <div>
    价格:<input type="text" name="price">
    </div>
    <div>
    作者:<input type="text" name="author.name">
    </div>
    <div>
    图书:<input type="file" name="file" value="file">
    </div>
    <div>
    封面:<input type="file" name="cover" value="cover">
    </div>
    <input type="submit" value="添加" style="align-self: center;">
</form>
</body>

<script type="text/javascript">
    $(function () {
        $("input:first").val(getLoginUserId());
    });

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
