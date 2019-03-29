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

CORS Support
Cross-origin resource sharing (CORS) is a W3C specification implemented by most browsers that lets
you specify in a flexible way what kind of cross-domain requests are authorized, instead of using some
less secure and less powerful approaches such as IFRAME or JSONP.
As of version 4.2, Spring MVC supports CORS. Using controller method CORS configuration with
@CrossOrigin annotations in your Spring Boot application does not require any specific configuration.
Global CORS configuration can be defined by registering a WebMvcConfigurer bean with a
customized addCorsMappings(CorsRegistry) method, as shown in the following example:

```
@Configuration
public class MyConfiguration {
@Bean
public WebMvcConfigurer corsConfigurer() {
return new WebMvcConfigurer() {
@Override
public void addCorsMappings(CorsRegistry registry) {
registry.addMapping("/api/**");
}
};
}
}
```
