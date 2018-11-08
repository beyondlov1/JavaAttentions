**表格实现灰白相间**

tr:NTH-CHILD(even) {
	background: gray;
}

tr:NTH-CHILD(odd) {
	background: white;
}


//tr经过时变红
tr:NTH-CHILD(odd):HOVER{
	color:red;
}

---

**实现table条目从两边插入**

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'show_all_books' starting page</title>

<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.6/jquery.min.js"></script>

<style type="text/css" >

tr{
display: block;
}
tr:NTH-CHILD(even) {
	background: orange;
	position: relative;
	left:-100%;
}

tr:NTH-CHILD(odd) {
	background: lightgray;
	position:relative;
	right:-100%;
}

tr:FIRST-CHILD{
background: lightgray;
}

tr~tr:HOVER{
	color:red;
}
</style>

</head>


<body>
	
	<table border="0" style="text-align: center; margin: auto;overflow: hidden;">
		<tbody>
			<tr>
				<th>BookId</th>
				<th>BookTitle</th>
				<th>AuthorName</th>
				<th>OwnerName</th>
			</tr>
			<c:forEach items="${list}" var="book">
				<tr>
					<td>${book.id }</td>
					<td>${book.title}</td>
					<td>${book.author.name }</td>
					<td>${book.owner.name }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>

<script type="text/javascript">
$(function(){
$("tr").animate({"left":"0","right":"0"}, 1000);
})
</script>
</html>


ps: tr不是块级元素, chrome不能实现上述效果,firefox可以, 如果想要chrome实现需要将tr强转为块级元素 tr{ display: block;}

ps2: 但是chrome用上述方法的话会出现tr中的表格状态都没有了, 文字不能对齐 , 连成一块

所以修改成下面的代码:

tr:NTH-CHILD(even) td {
	background: orange;
	position: relative;
	left:-100%;
}

tr:NTH-CHILD(odd) td {
	background: lightgray;
	position:relative;
	right:-100%;
}

<script type="text/javascript">
$(function(){
$("td").animate({"left":"0","right":"0"}, 1000);
})
</script>

----

**垂直居中**

[https://www.qianduan.net/css-to-achieve-the-vertical-center-of-the-five-kinds-of-methods/](https://www.qianduan.net/css-to-achieve-the-vertical-center-of-the-five-kinds-of-methods/)



````css
form {
	position:absolute;
	width:500px;
	height:200px;
	margin:auto;
	text-align:center;
	margin:auto;
	left:0;
	right:0;
	top:0;
	bottom:0;
}
````

----

#### 让<pre>标签文本自动换行

```css
pre{  
    white-space:pre-wrap;  
    white-space:-moz-pre-wrap;  
    white-space:-pre-wrap;  
    white-space:-o-pre-wrap;  
    word-wrap:break-word;  
    }  

父标签加个div
div{    
	word-wrap: break-word;
    white-space : normal;
}
```

#### 英文或者数字不能自动换行
添加以下css，ie8可用
.input-display{
	word-break: break-all;
	width:auto; 
	display:block; 
	white-space:pre-wrap;
	word-wrap : break-word ;
	overflow: hidden ;
}
