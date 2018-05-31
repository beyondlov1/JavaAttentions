# Spring #
---

## spring就是一个bean的工厂 ##

## 创建bean
- 直接在配置文件中配置
- 用工厂创建

## 创建bean工厂
- 静态的工厂
- 实例工厂
- 实现接口factoryBean接口的工厂

## 向bean中注值
- setter 配置文件中用property标签
- 构造方法参数 constructor-arg标签
- p命名空间 p:name  p:price 属性
- 注解的方式注值 @autowired, @qualifior/@resource
- 集合注值 list set map标签 map有key属性

## 创建bean后的动作
- init destroy方法
- postBeanProcessor接口

## 如何加载applicationContext.xml

**java**
- 在用的时候创建APPlicationContext context = new  ClassPathXml....

**web**

- Web项目中, 使用Spring默认的Listener, 在web.xml中配置listener: org.springframework.web.context.ContextLoaderListener和初始化参数context-param: contextConfigLocation
- 取值: 
	WebApplicationContext context = (WebApplicationContext) request.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

**JUnit**
	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration("classpath:applicationContext.xml")

顺便注值:
	@Resource(name = "bookAction")
	private BookAction bookAction;

## aop

- jdk实现方式: Proxy
- cglib: Enhancer setSuperClass setCallback create


spring中: pointcut aspect---往哪插,谁插
xml方法
- 传统aop: aspect pointcut advisor(pointcut aspect)
- AspectJ: aop:aspect(ref) pointcut aop:before after afterReturning afterThrowing around

注解方法:
- 直接在切面的类中加入 @Aspect @Component @Before ...
- xml中要扫描和自动创建代理

## 将上述基本的实用一下: jdbcTemplate

- spring 中配置dataSource org.springframework.jdbc.datasource.DriverManagerDataSource
- spring 中配置jdbcTemplate org.springframework.jdbc.core.JdbcTemplate

*这些包提供类, 有Spring生成对象*

## 换个数据源

*换hikari数据源没成功, 但c3p0没问题*

- 在配置文件中配置四大属性
- 将四大属性放在外置的文件中

## 手动编码使用transactionTemplate(不常用)

- 终极目标获得transactionTemplate

## 声明式处理事务 xml

- 利用aop插入事务(注意导包AOP的各种包:详见Tips/spring)

主要的环节为创建切面: 
- pointcut 
- aspect(tx包中已经写好: tx:advice,需要配置) 
- advisor

## 注解处理事务 

- tx:annotation-driver
- @transactional

## jdbcDaoSupport

- dao 可以继承jdbcDaoSupport 不用注入jdbcTemplate