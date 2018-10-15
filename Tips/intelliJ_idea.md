

**IntelliJ IDEA 2018.1 激活破解方法**

- 在打开的License Activation窗口中选择“License server”
- 在输入框输入下面的网址：  
http://idea.codebeta.cn  
或者http://idea.imsxm.com/  
- 点击：Activate即可激活。

---

**导入libaries**

- 右上角的project structure
- SDKs 点 + 

![](https://i.imgur.com/JNh9pEU.png)

---

**改变项目部署的根路径**

run->tomcat->deployment->applicationContext(右边)
![](https://i.imgur.com/4BlFZO3.png)

---

**intelliJ用eclipse快捷键**

切换keymap成eclipse模式


自动补全变量还需要改一下: 

- eclipse是ctrl+2;L

- intelliJ是ctrl+alt+v

参考:
https://darekkay.com/blog/intellij-idea-shortcuts-for-eclipse-users/

#### 熱部署JRebel

文件在百度網盤的裏

https://blog.csdn.net/qq_27093465/article/details/79148498

 #### idea同步项目到github

1. 安装git:  <http://git-scm.com/downloads> 

2. 更改idea的git路径, File-Settings-Version Control-Git  

3. 设置GitHub账号: Settings-Version Control-GitHub

4. 创建本地仓库: 对于没有在GitHub上创建仓库的一个本地项目，首先要在本地中创建本地仓库
   先选中整个项目目录，在菜单栏VCS-import into version control-Add添加一个本地仓库

5. Share to GitHub: 菜单栏VCS-Import into version control-share project on GitHub.

参考: https://blog.csdn.net/luoweifu/article/details/46779027

#### idea永久破解

1. 将破解包放到bin文件夹中， 更改idea64.exe.vmoptions文件，添加： -javaagent:破解包路径
2. 启动

破解包： ./ideaCrack/JetbrainsCrack-2.8-release-enc.jar