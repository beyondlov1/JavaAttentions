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
**upload**  
	
设置上传文件大小限制  总大小

    <constant name="struts.multipart.maxSize" value="20971520"></constant>

---
**struts.xml中设置限制 (upload为例)**  

名称为fileUpload.方法名首字母小写

        <action name="upload" class="com.beyond.demo4.UploadAction"
			method="upload">
			<result type="redirect">/index.jsp</result>
			<result name="input">/index.jsp</result>
			
			<interceptor-ref name="defaultStack">
				<param name="fileUpload.allowedExtensions">.txt,.doc</param>
			</interceptor-ref>
		</action>


---

**文件下载编码**  
firefox与chrome浏览器对中文文件名的编码不一样
base64 和 url

    import com.sun.org.apache.xml.internal.security.utils.Base64;
    import java.net.URLEncoder;

    public String getEncodeFileName(String filename, String agent) throws UnsupportedEncodingException {
		if (agent.toLowerCase().contains("firefox")) {
			return "=?UTF-8?B?" + Base64.encode(filename.getBytes("UTF-8")) + "?=";
		} else {
			return URLEncoder.encode(filename, "UTF-8");
		}
	}

---
**文件下载struts.xml**

    <action name="download" class="com.beyond.demo4.DownloadAction">
			<result name="input">/index.jsp</result>
			<result type="stream">
				<param name="contentType">${contentType}</param>
				<param name="contentDisposition">attachment;filename=${filename}</param>
			</result>
		</action>

${}中的要在Action中有get方法

---