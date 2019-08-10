

---
**init,destroy**

spring的配置文件中只要有一个对某个类设置了init和destroy方法, 这个类就有了init, destroy方法

---

**id和name的区别**

- name可以mybook1,mybook2算两个对象
- id只能mybook1,mybook2整体算一个对象

---

**cglib通过分析字节码来获取代理子类**

---

**传统aop导包**

	<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.9.1</version>
	</dependency>

---

**AspectJ是一个AOP的框架**

只要用aop就要导包

---
**用AspectJ的导包**

	<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.9.1</version>
		</dependency>
		<dependency>
			<groupId>aopalliance</groupId>
			<artifactId>aopalliance</artifactId>
			<version>1.0</version>
		</dependency>
	
		<dependency>
			<groupId>org.springframework</groupId>??
			<artifactId>spring-aspects</artifactId>
			<version>5.0.6.RELEASE</version>
		</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-aop</artifactId>
		<version>5.0.6.RELEASE</version>
	</dependency>

---

**注解AOP**

Aspect中的@AfterReturning,@AfterThrowing注解的方法中有returnValue, Exception的 **一定** 要在注解中加上相应的方法returning, throwing

---

**getBean得不到相应对象，而是得到接口的问题**


原因：如果某个类实现了一个接口，在代理时会默认先调用JDK的动态代理，所以只会得到接口对象。

解决方法：强制使用cglib进行代理，spring强制使用cglib的配置代码：

	<aop:config proxy-target-class="true"/>

---

### spring 3 与 jdk 1.8 不兼容!!!!!!! (待验证)

问题: java.lang.IllegalArgumentException
	at org.springframework.asm.ClassReader.<init>(Unknown Source)
	at org.springframework.asm.ClassReader.<init>(Unknown Source)
	at org.springframework.asm.ClassReader.<init>(Unknown Source)
	.....

原因: 不兼容

解决办法: 升级spring

#### spring+mybatis 多数据源配置

http://www.cnblogs.com/lzrabbit/p/3750803.html

### @configurable 可以用来配置spring容器以外的bean
可以将spring容器中的bean注入到自己新建的bean里面, 比如 new Foo() ...
spring boot 需要增加 @EnableSpringConfigured

maven:
     <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
        </dependency>
	
	 <plugin>
	            <groupId>org.codehaus.mojo</groupId>
	            <artifactId>aspectj-maven-plugin</artifactId>
	            <version>1.8</version>
	            <configuration>
	                <complianceLevel>1.8</complianceLevel>
	                <source>1.8</source>
	                <target>1.8</target>
	                <outxml>true</outxml>
	                <verbose>true</verbose>
	                <showWeaveInfo>true</showWeaveInfo>
	                <aspectLibraries>
	                    <aspectLibrary>
	                        <groupId>org.springframework</groupId>
	                        <artifactId>spring-aspects</artifactId>
	                    </aspectLibrary>
	                </aspectLibraries>
	            </configuration>
	            <executions>
	                <execution>
	                    <phase>process-classes</phase>
	                    <goals>
	                        <goal>compile</goal>
	                    </goals>
	                </execution>
	            </executions>
	        </plugin>

### spring boot 动态注册 bean

使用 ImportBeanDefinitionRegistrar 此接口在 BeanFactoryPostProcessor 中执行, 因此可以动态注册. 这个要配合 @Configuration , 在Configuration类上 @Import(XXXRegistrar.class)

里面可以用  ClassPathBeanDefinitionScanner 进行扫描注册, 添加filter后运行scan() 方法自动注册. 

demo: bean-definition-register-demos

参考: <https://www.jianshu.com/p/2b993ced6a4c> 

<https://zhuanlan.zhihu.com/p/30123517> 	

<https://blog.csdn.net/canyanruxue/article/details/81475473> 

ImportBeanDefinitionRegistrar  中接口方法中 importingClassMetadata 参数代表着 import的类, 也就是Configuration 类的注解