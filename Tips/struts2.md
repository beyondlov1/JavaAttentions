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
**jsp中获取单个验证表单field错误**  

    <s:fieldErrors>
    	<param>user.name</param>
    </s:fieldErrors>

---
**ognl**

jsp中使用ognl

    <s:property value="aaa"/>

想写字符串里面加单引号.

---

**<s:debug/>不能显示**  

修改常量  

    	<constant name="struts.devMode" value="true"></constant>  

---

**valueStack jsp两种写法**

	<s:property value="[0].top.username"/>
    <s:property value="username"/> //简写

---

**获取valueStack的两种方式**

	HttpServletRequest request = ServletActionContext.getRequest();
	ValueStack vs1 = (ValueStack) request.getAttribute("struts.valueStack");
/ 

    ValueStack vs = ActionContext.getContext().getValueStack();


---

**ognl+valueStack**

*ognl取值是从valueStack的root区域取值, Action封装数据会提前压到栈里, 如果Action中有私有的User属性, 则会以对象形式存储, 所以赋值和取值的时候name要带上对象名: user.username*

jsp中只有**<s:..>**能取到ognl表达式

压栈时**成员变量**也会在action被压的时候一起压进去, 而且action是较早压进去的(第二个压进去的)

成员变量要想和action同时压进栈需要设置**set和get**方法

java:

    public class ValueStackDemo extends ActionSupport {

	private User user = new User("username2", "password2");

	public String execute() {
		ValueStack vs = ActionContext.getContext().getValueStack();
		User user = new User();
		user.setUsername("username1");
		user.setPassword("password1");
		vs.push(user);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    }


jsp:

     <body>
       show valueStack
		<s:property value="[1].top.username"/>
       <s:property value="username"/>
       <s:property value="user.username"/>
       <input value="user.username">
       
       <s:debug></s:debug>
      </body>


---

**el表达式+valueStack**

el表达式能取到valueStack中的值

原因: struts对getAttribute方法进行了增强, 如果域里找不到就去valueStack中找

---

**xml配置文件可以放到项目的根目录下的文件夹中**

解析后项目文件夹下的resource中的东西都会在classes的根路径下

---

**validation xml验证**

*validate只需要在对应包下写 '类名-方法名-valideation.xml' 文件*

**type**:requiredstring, fieldexpression, int, email , regex  
**param name**(对应上边的type):_, expression, min/max, _, regex  

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE validators PUBLIC "-//Apache Struts//XWork Validator 1.0.2//EN" "http://struts.apache.org/dtds/xwork-validator-1.0.2.dtd">
    
    
    <validators>
    
    <!-- 字符串不为空 -->
    <field name="username">
    <field-validator type="requiredstring">
    <message>姓名不能为空</message>
    </field-validator>
    </field>
    
    <!-- 字符串不为空 -->
    <field name="password">
    <field-validator type="requiredstring">
    <message>密码不能为空</message>
    </field-validator>
    </field>
    
    <!-- 两个属性是否一致 ? 不知道有没有别的用法 -->
    <field name="repassword">
    <field-validator type="fieldexpression">
    <param name="expression">password==repassword</param>
    <message>两次密码不一致</message>
    </field-validator>
    </field>
    
    
    <!-- int大小限制 -->
    <field name="age">
    <field-validator type="int">
    <param name="min">18</param>
    <param name="max">120</param>
    <message>年龄不符要求</message>
    </field-validator>
    </field>
    
    <!-- email -->
    <field name="email">
    <field-validator type="email">
    <message>email不符要求</message>
    </field-validator>
    <field-validator type="requiredstring">
    <message>email不能为空</message>
    </field-validator>
    </field>
    <!-- phone -->
    <field name="phone">
    <field-validator type="regex">
    <param name="regex">^1\d{10}$</param>
    <message>phone不符要求</message>
    </field-validator>
    <field-validator type="requiredstring">
    <message>phone不能为空</message>
    </field-validator>
    </field>
    
    </validators>

---

**手动校验**

	public void validate() {
		if(username == null || username.trim().isEmpty()){
			// 在该位置直接向错误的区域中添加错误的信息
			this.addFieldError("username", "亲，用户名不能为空");
		}
	}

---

**文件上传java代码**

*java代码中就是获取到传上来的东西, 封装好, 我直接copyFile就可以了  
-----所以它的配置就和封装数据没什么区别, 可以添加拦截器, 判断一下文件类型*

属性:  
uploadFile  
uploadFileName  
uploadContentType  

用FileUtils.copyFile存到别的地方

---
**upload 常量**  
	
设置上传文件大小限制  总大小

    <constant name="struts.multipart.maxSize" value="20971520"></constant>

---

**文件下载struts.xml**

*文件下载就是通过传过来的filename找到文件, 准备好两个头一个流, 通过配置文件传回去就可以*

两个头一个流 都要在java代码中有**get**方法,然后xml配置文件中才能取到

xml的 result type 必须是**stream**

    <action name="download" class="com.beyond.demo4.DownloadAction">
			<result name="input">/index.jsp</result>
			<result type="stream">
				<param name="contentType">${contentType}</param>
				<param name="contentDisposition">attachment;filename=${filename}</param>
			</result>
		</action>

${}是通过Action中的get方法获取值的

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

**Filter 默认只拦截 request, 会导致 struts2 不能获取到转发资源**

要想获取到资源需要配置Filter, 在map中添加:  

    <dispatcher>REQEUST</dispatcher>  
    <dispatcher>FORWARD</dispatcher> 

---

**从一个Action转到另一个Action**

两种方法: chain(转发)  redirect/redirectAction

    <action name="BookAction_*" class="com.beyond.action.BookAction" method="{1}">
		<result>/show_all_books.jsp</result>
		<result name="SHOW" type="redirectAction">BookAction_showAllBooks.action</result>
		<allowed-methods>showAllBooks, addBook</allowed-methods>
	</action>

ps: struts中都不带项目名, action前面不加"/"
<result>中可以有<param>参数

---



