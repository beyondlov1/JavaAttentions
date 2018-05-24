
**忘记生成web.xml文件**

右键项目点击JavaEE工具下”generate deployment descriptor stub(生成部署描述文件)”

---

**项目不能部署, 一开tomcat就报错**

错误: "Publishing to Tomcat v9.0 Server at localhost...". Could not initialize class com.genuitec.eclipse.webclipse.livepreview.common.LPMappingService.....

原因: MyEclipse的bug

解决办法: 1) Shut down MyEclipse
2) Run Notepad as Administrator
3) From notepad, open the c:\windows\system32\drivers\etc\hosts file
**4) Add the line**
127.0.0.1 gapdebug.local.genuitec.com
5) Save the file (note, this will only work if Notepad has been run as Administrator)
6) Start MyEclipse.