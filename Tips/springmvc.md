#### springMVC 的 controller中获取 ApplicationContext 对象的方法

```java
WebApplicationContext context = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
```

原来struts2的方法就**不能**用了:

```java
WebApplicationContext context = (WebApplicationContext) request.getServletContext.getAttribute(WebApplicationContext.ROOT...);
```

 #### springMVC使用注解方式开发

xml文件中要加入: 

```xml
<mvc:annotation-driven></mvc:annotation-driven>
<context:component-scan base-package="com.beyond.web.action"></context:component-scan>
```

#### spring MVC 数据封装与 struts2 的不同

Spring MVC 中封装数据是直接把数据封装到对象中: 如

?username=beyond   ->   public void foo(User user);    如果写成 ?user.username 则不能封装到里面

所以, 如果一次提交中有相同的name属性, 则要找一个大bean封装两个对象, 从而用user.username的格式写.

#### springMVC 返回json

```java
@RequestMapping(value = "/hello.action")
	public @ResponseBody List<User> hello(Model model, User user) {
		List<User> list = new ArrayList<>();
		User user1 = new User();
		user1.setUsername("aaa");
		User user2 = new User();
		user2.setUsername("bbb");
		User user3 = new User();
		user3.setUsername("ccc");
		User user4 = new User();
		user4.setUsername("ddd");
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		return list;
	}
```

依赖包: 

```xml
<dependency>
		<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-core</artifactId>
		<version>2.9.5</version>
</dependency>

<dependency>
		<groupId>com.fasterxml.jackson.core</groupId>
		<artifactId>jackson-databind</artifactId>
		<version>2.9.5</version>
</dependency>
```

包: 

> jackson-core-2.9.5.jar
> jackson-databind-2.9.5.jar
> jackson-annotations-2.9.0.jar

ps: 不用在配置文件中声明, 直接就可以用 (5.0.7 release) (低版本可能需要)

#### 数据验证

JSR的方式(javax接口):

需要用到的实现包: Hibernate-Validator 

```java
@RequestMapping("/initRegister")
	public String initRegister(Model model) {
		model.addAttribute("user", new User());
		return "pages/register";
	}

	@RequestMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			List<FieldError> fieldErrors = errors.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				String message = fieldError.getDefaultMessage();
				String field = fieldError.getField();
				model.addAttribute(field + "Error", message);
			}
			return "pages/register";
		}
		userService.register(user);
		model.addAttribute("user", user);
		return "redirect:/pages/login.jsp";
	}
```

**形参有顺序:** @Valid @ModelAttribute("user") User user, Errors errors, Model model  **user 后边必须是 errors** 

**显示到jsp中:** 如果用spring的form标签要写上path 

```xml
<form:errors path="user.username"/>
```

#### 返回字符串时,中文会乱码

解决方法:

```java
 @RequestMapping(value = "/acceptOrder",produces = "text/plain; charset=utf-8")
```

#### springmvc封装数组,list等

1. 请求: ?name=xxx&name=xxx&id=xxx&id=xxx

   服务端: public String xxx(String[] id,String[] name)

   会自动放到数组里,但是如果多个表单中有一个不填会造成混乱(不推荐)

2. 请求: ?name[0].xxx=xxx

   服务端: public String xxx(UserModel users)    UserModel中存放一个List<User>

   这种方法要多创建一个UserModel的类, 比较麻烦, 而且如果索引中间少写一个,他也会给创建一个对象(不推荐)

3. 请求: json的数组形式 ( JSON.stringify(conditions)  要做一下字符串序列化)

   服务端: public List<User> testArgsArray(@RequestBody List<User> users)

   (推荐)

4. 请求: json的对象格式(字符串序列化)

   服务端: public List<User> testArgsArray(@RequestBody Map<String, Object> map) 

   可以返回map

5. 请求: data:{"listParam" : strList},   dataType:"json",

   服务端: public String requestList(@RequestParam("listParam[]") List<String> param) {     return "Request successful. Post param : List<String> - " + param.toString(); }

6. map只需要在服务端就爱上@RequestParam注解就可以了

#### spring mvc 拦截器

参考: https://blog.csdn.net/eson_15/article/details/51749880

### spring boot+ jsp
要加上这两个依赖
  <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
    </dependency>
    
    https://stackoverflow.com/questions/29782915/spring-boot-jsp-404
    https://hellokoding.com/spring-boot-hello-world-example-with-jsp/
