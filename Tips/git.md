# git配置 #
---
1. 安装git 客户端
2. (打开git bash) 获取ssh key
ssh-keygen -t rsa -C "your_email@youremail.com"
3. 之后会要求确认路径和输入密码，我们这使用默认的一路回车就行
4. 成功的话会在~/下生成.ssh文件夹，进去，打开id_rsa.pub，复制里面的key。 
5. 回到github上，进入 Account Settings（账户配置），左边选择SSH Keys，Add SSH Key,title随便填，粘贴在你电脑上生成的key。
6. 验证是否成功: ssh -T git@github.com
7. $ git config --global user.name "your name"
8. $ git config --global user.email "your_email@youremail.com"
9. 进入要上传的仓库(JavaAttentions)，右键git bash，添加远程地址：  
$ git remote add origin git@github.com:your_name/JavaAttentions.git
10. 在要上传的仓库文件夹中右键打开Git GUI 
11. rescan: 查看目录下修改的文件  
12. stage to commit: 在commit菜单下, 也可以快捷键 ctrl-T(不过貌似不能用)
13. 写上Commit Message: 格式(第一行写做了什么, 第二行空格, 第三行写有什么好处)  
14. push *如果push不了 , 提示下次commit前 pull 一下就 == fetch+local merge*

----------

#### 如果github上已经有了这个项目（比如从其他项目fork过来的）

可能会出现push失败的情况， 可以用gitGUI工具push， 选中Force overwrite 。。。

![1535353845802](C:\Users\huayu\AppData\Local\Temp\1535353845802.png)

如果不行就再试一次， 出来github的登陆框应该就行了

#### 
git 国内下载地址： https://github.com/waylau/git-for-win



### git clone慢(没试过还)

git config --global http.https://github.com.proxy https://127.0.0.1:1080 

git config --global https.https://github.com.proxy https://127.0.0.1:1080

或者

host文件添加：
192.30.253.113  github.com 
151.101.185.194 github.global.ssl.fastly.net 
192.30.253.120  codeload.github.com
参考：https://www.zhihu.com/question/276143842

####  Github上怎么修改别人的项目并且提交给原作者

https://blog.csdn.net/qq_26787115/article/details/52133008

#### git 用 ssh
https://blog.csdn.net/Felicity294250051/article/details/53606158

### 修改名字邮箱
git config --global user.name "zhang san"

git config --global user.email john@example.com

修改一个 把 global 去掉

参考（git笔记）：https://www.jianshu.com/p/e405f9857b52


### 文档路径过深
    git config --system core.longpaths true  
