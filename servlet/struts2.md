## tips ##

**未登录不能访问资源: 自定义拦截器**    

继承AbstractInterceptor   
在package中注册  
在action标签中使用 (注意要添加默认的defaultStack)  
 
    <package name="book" extends="struts-default" namespace="/book">
		<interceptors>
			<interceptor name="LoginInterceptor"
				class="com.beyond.interceptor.LoginInterceptor"></interceptor>
		</interceptors>
		<global-results>
			<result name="input">/pages/login.jsp</result>
		</global-results>
		<action name="*" class="com.beyond.action.BookAction" method="{1}">
			<interceptor-ref name="LoginInterceptor"></interceptor-ref>
			<interceptor-ref name="defaultStack"></interceptor-ref>
			<allowed-methods>findAllBook, deleteBook</allowed-methods>
		</action>
	</package>

---
用属性驱动封装对象要将html中 表单的name都改为**ognl**的语法, 包括**GET和POST**传参数中的name也要用ognl: user.username**  (AJAX要更加注意)

---
action的struts.xml中采用通配符配置action要在<action>中加入**<allowed-methods>**  2.5版本

---
**自动登录**  

servlet: cookie中添加username , 判断cookie是否存在 , **要设置cookie的path**, 否则不在同一根目录的servlet不能访问到

struts2: 设置拦截器, 先判断session中有没有, 再判断cookie中有没有自动登录. 登录后加入session, 看情况判断是否加入自动登录的cookie, 如果用户取消自动登录则删除cookie

---
