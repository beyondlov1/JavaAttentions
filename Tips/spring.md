

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


