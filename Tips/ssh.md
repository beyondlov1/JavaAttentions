### struts+spring+maven

版本: struts 2.5.26 , spring 5.0.6

直接用maven报错: org/springframework/core/ErrorCode ...

原因:  struts2-spring-plugin 依赖着低版本的 spring, maven会自动加载, 导致jar包重复, 从而产生错误
> The current version (2.5.10.1) of the Struts 2 Spring plugin has transitive dependencies to the Spring 4.1.6.RELEASE version. If you want to use the latest version of Spring, then you should exclude the transitive dependencies in your pom.xml for the Struts 2 Spring plugin and then declare dependency nodes to the current version of the Spring jar files. If you are using Ant and explicitly including the jar files in your application, then just include the latest version of the Spring jar files.
参考:[https://struts.apache.org/getting-started/spring.html](https://struts.apache.org/getting-started/spring.html)

解决办法: maven导入 struts2-spring-plugin 时需要在pom.xml中排除冲突的包 (可以查看 struts2-spring-plugin 的pom依赖文件, 查找那些包冲突), 


demo: 

	<dependency>
	        <groupId>org.apache.struts</groupId>
	        <artifactId>struts2-spring-plugin</artifactId>
	        <version>2.5.16</version>
	
	        <exclusions>
	            <exclusion>
	                <groupId>org.springframework</groupId>
	                <artifactId>spring-core</artifactId>
	            </exclusion>
	            <exclusion>
	                <groupId>org.springframework</groupId>
	                <artifactId>spring-beans</artifactId>
	            </exclusion>
	            <exclusion>
	                <groupId>org.springframework</groupId>
	                <artifactId>spring-context</artifactId>
	            </exclusion>
	            <exclusion>
	                <groupId>org.springframework</groupId>
	                <artifactId>spring-web</artifactId>
	            </exclusion>
	            <exclusion>
	                <groupId>org.springframework</groupId>
	                <artifactId>spring-test</artifactId>
	            </exclusion>
	        </exclusions>
	    </dependency>

---

### Spring+Hibernate(超级大坑)

版本: spring 5.0.6 , hibernate 5.2.1.7-Final

报错: 

	org.hibernate.HibernateException: createQuery is not valid without active transaction (或者其他与事务相关的报错)

原因: 

	1. <property name="hibernate.current_session_context_class">thread</property>
	hibernate.current_session_context_class是用来绑定一个上下文, 从而获取到同一个session, 写入thread代表绑定到当前线程, 而与Spring整合时, 此session则不在spring有事务代理的的那个上下文中, 从而不会自动产生事务.
	   
	2. transactionManager要用org.springframework.orm.hibernate5.HibernateTransactionManager, 用jdbc的也会报错


解决办法: 

	1. 将 <property name="hibernate.current_session_context_class">thread</property> 在hibernate.cfg.xml中删除.
	
	2. transactionManager用org.springframework.orm.hibernate5.HibernateTransactionManager


>ps_1: 在ssh2中的sessionFactory配置文件中应将hibernate.current_session_context_class设为org.springframework.orm.hibernate3.SpringSessionContext（默认为此值），并应用spring管理事务.  
>ps_2: 在spring的类LocalSessionFactoryBean源码，方法buildSessionFactory中	hibernate.current_session_context_class设为了		org.springframework.orm.hibernate3.SpringSessionContext

参考:  
https://blog.csdn.net/yinjian520/article/details/8666695  
http://justsee.iteye.com/blog/1061576

---

### Spring+Hibernate+Hikari(待解决)

---

### maven 文件路径

spring配置文件中加载*.hbm.xml问题

	</property>
	<!-- 配置映射文件 
	mappingDirectoryLocations : 设置目录,加载目录下的所有hbm.xml
				classpath:cn/itcast/domain/
			mappingJarLocations : 加载jar包映射文件
			mappingLocations : 指定加载哪一些文件
				classpath:cn/itcast/domain/User.hbm.xml
				classpath:cn/itcast/domain/*.hbm.xml
			mappingResources :加载src下内容
				cn/itcast/domain/User.hbm.xml
		-->
	<property name="mappingResources" value="cn/itcast/domain/User.hbm.xml"></property>



---

### spring 配置文件中加载 jdbc.properties 问题

错误: 在spring配置文件中用context:property-placeholder加载jdbc文件不好使:

	<context:property-placeholder location="classpath:jdbc.properties" /> 


原因: 未知

解决方法:
	
	<bean id="jdbcConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations" value="classpath:jdbc.properties" />
	</bean>

### hibernateTemplate 的坑

	hibernateTemplate.findByExample(user);
	返回值为List<User>; 
	如果查不到对应的user, 返回[], 而不是null;
	需要isEmpty()判断一下;

### hibernateTemplate查询时，DetachCriteria的创建

	DetachedCriteria criteria = DetachedCriteria.forClass(clazz);


### spring配置文件中hibernate加载hbm.xml问题

错误：spring配置文件中包含hibernate配置时，加载hbm.xml文件可能会出现问题：
org.hibernate.boot.InvalidMappingException: Could not parse mapping document: null (INPUT_STREAM)

原因：可能是复制的项目，导致出现问题

解决办法：在spring中import hbm文件就会好；
不过，貌似不改也可以，完全莫名奇妙。。。。

### spring和hibernate整合时提示dataSource问题
错误：UnknownUnwrapTypeException: Cannot unwrap to requested type [javax.sql.DataSource]

原因：spring配置事务需要dataSource，如果spring中没有配置dataSource就需要在hibernate中配置。

解决办法：hibernate中配置

	<property name="hibernate.connection.provider_class">org.hibernate.hikaricp.internal.HikariCPConnectionProvider</property>
	
	需要找到可用的dataSource否则还是要报错。

### spring配置中提示ContextLoaderListener找不到但是明明有相应libary

解决办法：在lib中导入包，而不要靠添加libary（不靠谱）

### applicationContext.xml文件沒找到問題

可能是裏面import的問題，可以嘗試在import路徑前加入classpath

### spirng中action一定要写成多例

如果不寫成多例，struts2就不會賦值，值棧不會更新

### jquery+xStream+ssh

xStream 对于内部同一对象会使用相同的节点的**引用**，如：

```xml
xStream 的 mode  默认采用 XPATH_RELATIVE_REFERENCES:
<cd reference="../cd[2]"/>
```

可以改成

```java
xstream.setMode(XStream.ID_REFERENCES);
```

效果:

```xml
<category class="com.beyond.entity.Category_$$_jvstfd8_1" reference="5"/>
```

这种方法需要jquery中根据id另行解析:

```js
if (categoryName == null || categoryName == "") {
	var categoryId = $(bookArray[i]).find("category").attr("reference");
	categoryName = $(data).find("#" + categoryId).find("categoryName").text();
}
```



注意: 不能改成No references, 如果两个类相互关联会造成死循环

>  it's impossible to support back-references (cycles) with NO_REFERENCES mode activated therefore a CircularReferenceException is thrown if you ever try to do so. 

参考官方文档: http://x-stream.github.io/graphs.html

总结: 这种方法虽然能解决, 但是jquery中需要对每个关联的类都进行上面的解析, 麻烦, 所以还是用jsp吧 !!!!!

