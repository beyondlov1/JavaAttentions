# springMVC

### springMVC与struts2所做的事情差不多 , 属于web层的框架

区别: 

- 单例, 多例
- servlet, filter
- model, valueStack
- 轻, 慢

#### 流程

handler mapping 

adapter

action (Controller) 

View resolver

#### 配置文件也就是写上面的几个

action 一定要配

```xm
<bean name="/index.action" class="com.beyond.web.action.UserAction"></bean>
```

#### 配置文件加载

在 web.xml 中的DispatcherServlet中配置, 默认名: <servlet-name>-servelt.xml

本质就是个spring配置文件, 包含在其他配置文件中加载,或者加载别的配置文件也可以

#### 创建action

- 实现Controller接口

- 添加@Controller注解

  ```xml
  <mvc:annotation-driven></mvc:annotation-driven>
  <context:component-scan base-package="com.beyond.web.action"></context:component-scan>
  ```

#### 映射

注解@RequestMapping , 在class上或者在方法上

#### 返回结果

model 中添加返回的值, return String 确定返回的路径 (看有没有配置View Resolver)

#### 封装数据

用方法参数的方法:

- User user, String username -> html中name都为username
- 方法中可以写HttpServletRequest HttpSession 之类的
- 转换类型 @InitBind 

#### 转发和重定向

return "forward: ..."

return "redirect: ..."

#### 返回json

导入jackson的包

> jackson-core-2.9.5.jar
> jackson-databind-2.9.5.jar
> jackson-annotations-2.9.0.jar

用注解@ResponseBody

#### 数据验证

两种方法, 但有共通之处

**JSR的方法(最简单版):** 

1. 开启mvc注解

2. 在pojo类中添加validation的注解

3. 在action的方法中添加形参: 

   ```java
   @RequestMapping("/login")
   public String login(@Valid User user, Errors errors, Model model) {
   	....
   }
   or
   @RequestMapping("/login")
   public String login(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
   	....
   }
   (暂时还不知道 @ModelAttribute("user") 有什么用)
   ```

4. 在jsp中显示信息

   ```xml
   <form:errors path="user.username"/>
   ```
   不能直接访问jsp, 要写一个initRegister.action 跳转

**spring 的 validation:**

1. 与上面的区别在于 注解用的 @Validated, 而且这个注解可以有参数
2. 当pojo类中的验证添加了groups属性(可以整个空的interface来作为参数)时, @Valid 则不能利用这个验证, 而@Validated可以通过添加value属性, 只利用相应的验证

**通用的pro:**

1. 配置 Validator : 

   配置主要集中在两点: 用什么验证器的实现类     -      弹出什么消息

   ```xml
   <!-- validator -->
   <bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">
   	<property name="providerClass" value="org.hibernate.validator.HibernateValidator"></property>
   	<property name="validationMessageSource" ref="validationMessageSource"></property>
   </bean>
   
   <!--读取文件获取消息.properties or .xml-->
   <bean id="validationMessageSource"
   	class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
   	<property name="basenames">
   		<list>
   			<value>classpath:customValidationMessage</value>
   		</list>
   	</property>
   	<property name="fileEncodings" value="utf-8"></property>
   	<property name="cacheSeconds" value="120"></property>
   </bean>
   ```

2. mvc 注解中应用validator,  注解中注入什么validator, 注解的方法就用什么validator, 不管是@Valid 还是 @Validated

**ps:**  比较来说, 这两种方法区别不大, 但是 @Validated 因为支持分组, 所以更加灵活, 比如 : pojo类中有一堆验证方法, 而在register 方法中需要验证密码,  而login方法中不需要验证密码, 则可以直接用添加分组的方法来解决. @Valid的方法只要一添加, 就会验证里面所有未带分组的属性, 暂时没有比较好的解决上述情景的方法.

参考 :https://www.jianshu.com/p/09e68aacbc4d