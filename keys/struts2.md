# strut2

struts2就是一个拦截器，帮助进行数据封装，数据验证，转发重定向

## web.xml配置
	<filter>
		<filter-name>struts2</filter-name>
		<filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>struts2</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

## 写Action类

三种写法：POJO类，实现Action接口，集成ActionSupport

## 配置struts.xml

struts2 package[name, extends,namespace] action[name, class, method] result allowed-methods

## 封装数据

属性驱动，模型驱动（实现ModelDriven接口）  
数据封装到集合（List---list[0].name，Map---map["key"].name）  
上传文件（直接已经封装成一个File，只需要copy就行）

## 数据验证

手动验证：validate(), validateMethod(); addFieldError()

xml验证：类名-方法路径名(与struts2中的name对应)-validation.xml  
参考：https://struts.apache.org/core-developers/using-field-validators.html

## 自定义拦截器

集成AbstractInterceptor ai.invoke()放行

## 文件上传

封装三个参数： File 表单name, 表单name+ContentType, 表单name+FileName

demo:

	File upload;
	String uploadContentType;
	String uploadFileName;

注意文件上传大小限制：（可在页面s:ActionError查看错误）

	可以在struts.xml中设置常量，修改文件上传的默认总大小！！！
            * <constant name="struts.multipart.maxSize" value="5000000"></constant>

## 下载

两个头一个流：ContentType, contentDisposition 

要在Action中加入这三个元素的get方法

ps:文件名的中文乱码问题

## 值栈

root contextMap

ActionContext和ValueStack

获取valueStack：三种方法

存值：valueStack.push(Object obj)
valueStack.set(key,value)

el访问到valueStack：对request增强，找不到就去值栈找

符号：$ % #













