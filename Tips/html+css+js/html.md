#### 浏览器缓存: firefox显示304, chrome显示200(不知道为什么)

firefox自动缓存静态数据

#### form 中用button来提交表单

```html
<form action="${pageContext.request.contextPath}/hello">
<input name="name" value="you" type="text">
<button type="submit">value</button></form>
```


#### MyEclipse中html中文乱码的问题

MyEclipse中尽管都设置了utf-8，但是仍然会出现乱码

原因：head中的meta设置的不对，不是name 而是 **http-equiv**

```html
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
```

### 自适应页面
http://www.ruanyifeng.com/blog/2012/05/responsive_web_design.html



