<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<a href="${requestScope.page.url}&currentPageNum=1">首页</a>
	<span>第${page.currentPageNum }页</span>
	<a href="${requestScope.page.url}&currentPageNum=${page.prePageNum }">上一页</a>
	<a href="${requestScope.page.url}&currentPageNum=${page.nextPageNum }">下一页</a>
	<c:forEach begin="${page.displayStartPageNum }" end="${page.displayEndPageNum }" var="num">
	<a href="${requestScope.page.url}&currentPageNum=${num }">${num }</a>
	</c:forEach>
	<a href="${requestScope.page.url}&currentPageNum=${page.totalPageNum }">末页</a>
	<input id="num" type="text" name="currentPageNum">
	<input id="jump" type="button" value="跳转" onclick="jump()">
</div>

<script type="text/javascript">
function jump(){
var num = document.getElementById("num").value;
var reg = /^[1-9][0-9]*$/;
if(reg.test(num)){
var newNum = parseInt(num);
if(newNum>${page.totalPageNum}||newNum<1){
alert("请输入正确文字");
return;
}else{
window.location = "${requestScope.page.url}"+"&currentPageNum="+newNum;
return;
}
}
}
</script>
