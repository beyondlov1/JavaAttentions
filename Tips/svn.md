## 安装

1. subversion (svn --version 檢測是否成功)
2. TortoiseSVN （圖形界面工具，可選）
3. Eclipse插件：eclipse_svn_site

### 创建windows服务 
cmd指令：
sc create svn binpath= "D:\java\Subversion\bin\svnserve.exe --service -r F:\repository\svn" displayname= "SVN-Service" start= auto depend= Tcpip

### 客户端版本要低于服务端的版本，否则用客户端建库可能会有问题：

....要求1-6， 发现8....

