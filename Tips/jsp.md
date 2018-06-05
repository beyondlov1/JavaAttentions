## jsp:include 可以传参，但只能传String

参考：https://beginnersbook.com/2013/12/jsp-include-with-parameter-example/

示例：

	index.jsp

	<html>
	<head>
	<title>JSP include with parameters 	example</title>
	</head>
	<body>
	<jsp:include page="file.jsp" >
	<jsp:param name="firstname" 	value="Chaitanya" />
	<jsp:param name="middlename" value="Pratap" />
	<jsp:param name="lastname" value="Singh" />
	</jsp:include>
	</body>
	</html>


	file.jsp

	${param.firstname}<br>
	${param.middlename}<br>
	${param.lastname}