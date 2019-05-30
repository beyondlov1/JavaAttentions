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


### spring core
- BeanWrapper 这个接口用来将属性转化为bean， 实现类: BeanWrapperImpl 解决问题： 将属性放到bean里

- PropertyEditor 用来转化新的类型， 将属性中的解析为bean中的属性 自定义: 继承PropertyEditorSupport， 并注册

---

- BeanFactory 这个接口是BeanWrapper的扩展， 解决获取属性的问题。beanWrapper 解决了单个bean的加载问题， beanFactory 则解决多个bean的加载问题。

  - 获取属性: 解决对策-PropertiesBeanDefinitionReader 可以从外部资源加载属性值, 实现类-DefaultListableBeanFactory(properties)  XmlBeanFactory(xml)
  - bean属性值及依赖关系: 解决对策-在xml或者properties文件中配置
  - bean创建模式：是否为单例  解决对策-在xml或者properties文件中配置
  - 初始化和销毁的方法  解决对策-在xml或者properties文件中配置, 可配置init和destroy方法
  - bean的依赖关系 解决对策-在xml或者properties文件中配置

---

**PropertiesBeanDefinitionReader**: 读取properties
从Resource中读取bean的属性, 并调用loadBeanDefinition(BeanFactory)加载到factory中. (这个方法不是由factory调用的原因可能是: factory更加专注于生产bean, 如何生产bean, 生产出什么样的bean. 并不倾向于关注factory的初始化)  

**XmlBeanDefinitionReader**: 读取xml

---

- **BeanFactoryPostProcessor**: BeanFactory 也可以用PropertyEditor进行类型转换， 但是每次都要写好几个比较麻烦， 所以还可以用BeanFactoryPostProcessor, 实现类CustomEditorConfigurer。
  这个类里面可以有个属性： cutomEditors 用来存放各种PropertyEditor， 之后注册到BeanFactory中就可以进行类型转化了

- FactoryBean 是一个比较有用的接口， 实现类： MethodInvokingFactoryBean， 这个类可以执行某一个方法， 从而达到改变容器中某一个bean中的属性的目的
  相当从文件中获取到bean到真正获取bean之间的中间层， 可以在这个中间层中对这个bean进行一些操作， 比如：给属性赋值

- ApplicationContext 是BeanFactory的扩展
  - 自动创建所有单例对象

  - 自动加载PostProcessor类， 进行类型转化

  - 同时载入多个配置文件

  - 资源载入

  - 国际化

  - 事件传播

    > 企业级服务：电子邮件、JNDI、EJB、远程调用、定是服务、模板框架（FreeMarker，Velocity
    >
    > ）

##### spring event

见demo

@EventListener 的解析类：org.springframework.context.event.EventListenerMethodProcessor

实现BeanFactoryPostProcessor接口

##### bean生命周期

1. instanitate 实例化
2. populateProperties
3. BeanNameAware setBeanName()
4. BeanFactoryAware setBeanFactory()
5. ApplicationContextAware setApplicationContext()   (just in context)
6. Pre-initialization BeanPostProcessors postProcessBeforeInitialization()
7. InitializingBean afterPropertiesSet()
8. init-method
9. Post-initialization BeanPostProcessors postProcessAfterInitialization()
10. ready GO!

---

1. Container is shutdown
2. disposableBean destroy()
3. destroy-method
4. end!

