
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

---

**jsp自动提示**

MyEclipse：

9.0以后版本的MyEclipse对html和jsp的代码提示

1、File-->Export-->General-->Preferences 点击下一步，将这个配置文件导出到桌面上，和（一）中的5,6一样

2、在桌面上使用文本编辑器打开此文件，在里面添加如下一行内容后保存

/instance/org.eclipse.wst.html.ui/autoProposeCode=<= abcdefghijklmnopqrstuvwxyz:

3、然后将此配置文件导入，File-->Import-->General-->Preferences，就是第一步的逆操作。

參考：https://www.jianshu.com/p/d1a9b472e5e0

---

**XML自動提示**

打 开 Eclipse 依次选择 Window > Preferences > Xml > Xml Files > Editor > Content Assist > Auto activation > Prompt when these characters are inserted ，设置框中默认是 <=: ，

现在将它改为：

以下为引用内容：  <=:. abcdefghijklmnopqrstuvwxyz(,

https://blog.csdn.net/guyuealian/article/details/50767391

---

**MyEclipse生成hibernate**

MyEclipse反向生成POJO類和配置文件會有一些問題，需要手動改：
1. generator 中不能按myeclipse中的选择uuid.string，会出错-----解决：要用uuid
2. 生成的配置文件session不会绑定thread，要手动配置
3. mapping配置中没有级联关系-----解决：用的时候一方多方都要保存

---

**MyEclipse中html中文乱码问题**
将meta中的name改为 http-equiv

```html
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
```

---

#### MyEclipse 修改web项目根路径

1.Web项目右键->Properties->Myeclipse->Web->Web Context-root.

2.在项目中打开.project文件,修改<projectDescription>下的<name>名称.



#### Eclispe快捷键

- ctrl+shift+h  搜索类

#### MyEclipse创建xml模板 (提示框中的)

window - property - XML Source - Templates - New

#### 转换大小写的快捷键

Eclipse中改变字符串大小写的快捷键：

小写变大写：ctrl＋shift＋x

大写变小写：ctrl＋shift＋y

