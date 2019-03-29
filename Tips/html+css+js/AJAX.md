# 大小写	
	XMLHttpRequest
	responseXML
	(XML是大写,大写!!!!)

# 括号
	xmlHttpRequest.onreadystatechange = method;
	方法名不能加括号

# 跨域

参考： https://www.jianshu.com/p/a9cd5d74d9ba

## 亲测有效: spring-boot 中有 @CrossOrigin 的注解(CORS)可以支持跨域
用法: 
spring-boot:Controller:
```
  @CrossOrigin
    @RequestMapping("/testPropertyString")
    public Object testPropertyString(){
        return serviceDemo.getPropertyDemo().getName();
    }
```
ajax:
```
function query(callback){
        $.ajax({
            type : "post",
            url : "http://172.16.193.47:8080/testPropertyString",
            contentType: "application/json",
            dataType : "text", //返回数据类型
            success : callback,
            error : function() {
                console.log("请求失败");
            }
        });
    }
```
