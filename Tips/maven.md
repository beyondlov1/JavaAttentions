版本:eclipse Mars, maven

使用maven创建web项目: 自己项目-右键-buildPath-liberies标签-addLibary-添加webAppLibaries,ServerRuntime(否则jsp会报错没有HttpServlet)

pom.xml 报错:org.apache.maven.archiver.MavenArchiver.getManifest(org.apache.maven.project.MavenProject, org.apache.maven.archiver.MavenArchiveConfiguration) pom.xml /<maven projectName> line 1 Maven Configuration Problem

解决办法:

Better solution: update Eclipse m2e extensions
From Help > Install New Software.., add a new repository (via the Add.. option), pointing to any of the following URLs:
https://otto.takari.io/content/sites/m2e.extras/m2eclipse-mavenarchiver/0.17.2/N/LATEST/   (不可用)or
http://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-mavenarchiver/0.17.2/N/LATEST/  (可用)
Then follow the update wizard as usual. Eclipse would then require a restart. Afterwards, a further Update Project.. on the concerned Maven project would remove any error and your Maven build could then enjoy the benefit of the latest maven-jar-plugin version.

参考: https://stackoverflow.com/questions/37555557/m2e-error-in-mavenarchiver-getmanifest

---

**pom.xml中写入依赖后不自动下载, 且报错**

maven的配置, 将auto download 打开

https://blog.csdn.net/J080624/article/details/71747310



---

#### Eclipse 创建 maven项目

1. Help -> install from site (更新一下m2e什么的)

2. http://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-mavenarchiver/0.17.2/N/LATEST/

3. 等(第一次可能不成功, 多试两次)

4. 创建maven项目

   如果提示内存不足什么的可能是联网有问题

   在.m2文件夹中添加一个settings.xml可能能解决问题:

   ```xml
       <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"  
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
                xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0  
                   http://maven.apache.org/xsd/settings-1.0.0.xsd">  
             
           <!-- 这个是配置阿里Maven镜像 -->  
           <mirrors>  
               <mirror>  
                 <id>aliyun</id>  
                 <name>aliyun</name>  
                 <url>http://maven.aliyun.com/nexus/content/groups/public/</url>  
                 <mirrorOf>central</mirrorOf>  
               </mirror>  
           </mirrors>  
             
           <profiles>  
               <profile>  
                   <id>nexus</id>  
                   <repositories>  
                       <repository>  
                           <id>central</id>  
                           <url>http://repo.maven.apache.org/maven2</url>  
                           <snapshots>  
                               <enabled>false</enabled>  
                           </snapshots>  
                       </repository>  
                       <repository>  
                           <id>ansj-repo</id>  
                           <name>ansj Repository</name>  
                           <url>http://maven.nlpcn.org/</url>  
                           <snapshots>  
                               <enabled>false</enabled>  
                           </snapshots>  
                       </repository>  
                   </repositories>  
               </profile>  
           </profiles>  
         
           <activeProfiles>  
               <activeProfile>nexus</activeProfile>  
           </activeProfiles>  
       </settings>  
   ```

5. 创建maven项目

   https://mvnrepository.com/

6. 要改成web项目: 项目上右键 - configure facets - manage - 勾选 Dynamic Web Module



#### ClassNotFound 但是明明能找到

You need to add the "Maven Dependency" in the Deployment Assembly

- right click on your project and choose properties.
- click  on Deployment Assembly.
- click add
- click on "Java Build Path Entries"
- select Maven Dependencies"
- click Finish.

Rebuild and deploy again

Note: This is also applicable for *non maven* project.

#### 添加jstl

```xml
 <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
```

#### maven 源码包 中文乱码

- 修改Eclipse中文本文件的默认编码：windows->Preferences->general->Workspace->Text file encoding设置为UTF-8
- 修改JAVA源文件的默认编码：windows->Preferences->general->Content Types->右侧Context Types树，点开Text，选择Java Source File，在下面的Default encoding输入框中输入UTF-8，点Update；
- 如果还是乱码，记得重启eclipse 。

参考： https://blog.csdn.net/zsw12013/article/details/51502876