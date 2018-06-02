<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/5/30
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>showAllUser</title>
</head>
<body>
<table>
    <tbody>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>
                    ${user.id}
            </td>
            <td>
                    ${user.username}
            </td>
            <td>
                    ${user.password}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
