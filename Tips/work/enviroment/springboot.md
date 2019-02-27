#### install

MyEclipse：

1. 创建maven项目
2. 修改pom.xml
   - 可以上 https://start.spring.io/ 生成一个pom.xml文件
3. 初始化項目
   1. 编写一个普通类，在类上添加@SpringBootApplication的注解
   2. 在类上加上@RestController的注解，相当于：@Controller+@ResponseBody的综合，里面所有的方法都返回json数据



参考：

http://tengj.top/2017/02/26/springboot1/

https://blog.csdn.net/u012702547/article/details/53740047

#### 熱部署

eclipse中 pom.xml中添加這兩個依賴

```
<!--熱部署 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<!-- optional=true,依赖不会传递，该项目依赖devtools；之后依赖myboot项目的项目如果想要使用devtools，需要重新引入 -->
	<optional>true</optional>
</dependency>

<plugin>
	<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
		<configuration>
			<fork>true</fork>
		</configuration>
</plugin>
```

idea參考以下網址

參考 ： https://blog.csdn.net/ikownyou/article/details/79500056



#### 页面重定向

（并不是真正意义上的跳转，只是显示静态的资源）

spring boot 的controller中的方法可以返回静态资源, 但是没有看到其他的转发方式。这样可以实现前后端分离

方法: 

1. 在resource的包中添加static文件夹

2. 在controller中返回资源

   ```
   @Controller
   public class RedirectController {
   
   	@RequestMapping("/toIndex")
   	public String testRedirect() {
   		return "index.html";
   	}
   }
   ```

   @controller下面不能添加@RequestMapping (还不知道原因)

### spring - boot bug 
org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)

pom.xml build标签中加入

```
<resource>  
        <directory>src/main/java</directory>  
        <includes>  
          <include>**/*.xml</include>  
        </includes>  
      </resource> 
```