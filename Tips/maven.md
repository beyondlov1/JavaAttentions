�汾:eclipse Mars, maven

ʹ��maven����web��Ŀ: �Լ���Ŀ-�Ҽ�-buildPath-liberies��ǩ-addLibary-���webAppLibaries,ServerRuntime(����jsp�ᱨ��û��HttpServlet)

pom.xml ����:org.apache.maven.archiver.MavenArchiver.getManifest(org.apache.maven.project.MavenProject, org.apache.maven.archiver.MavenArchiveConfiguration) pom.xml /<maven projectName> line 1 Maven Configuration Problem

����취:

Better solution: update Eclipse m2e extensions
From Help > Install New Software.., add a new repository (via the Add.. option), pointing to any of the following URLs:
https://otto.takari.io/content/sites/m2e.extras/m2eclipse-mavenarchiver/0.17.2/N/LATEST/   (������)or
http://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-mavenarchiver/0.17.2/N/LATEST/  (����)
Then follow the update wizard as usual. Eclipse would then require a restart. Afterwards, a further Update Project.. on the concerned Maven project would remove any error and your Maven build could then enjoy the benefit of the latest maven-jar-plugin version.

�ο�: https://stackoverflow.com/questions/37555557/m2e-error-in-mavenarchiver-getmanifest

---

**pom.xml��д���������Զ�����, �ұ���**

maven������, ��auto download ��

https://blog.csdn.net/J080624/article/details/71747310



---

#### Eclipse ���� maven��Ŀ

1. Help -> install from site (����һ��m2eʲô��)

2. http://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-mavenarchiver/0.17.2/N/LATEST/

3. ��(��һ�ο��ܲ��ɹ�, ��������)

4. ����maven��Ŀ

   �����ʾ�ڴ治��ʲô�Ŀ���������������

   ��.m2�ļ��������һ��settings.xml�����ܽ������:

   ```xml
       <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"  
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
                xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0  
                   http://maven.apache.org/xsd/settings-1.0.0.xsd">  
             
           <!-- ��������ð���Maven���� -->  
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

5. ����maven��Ŀ

   https://mvnrepository.com/

6. Ҫ�ĳ�web��Ŀ: ��Ŀ���Ҽ� - configure facets - manage - ��ѡ Dynamic Web Module



#### ClassNotFound �����������ҵ�

You need to add the "Maven Dependency" in the Deployment Assembly

- right click on your project and choose properties.
- click  on Deployment Assembly.
- click add
- click on "Java Build Path Entries"
- select Maven Dependencies"
- click Finish.

Rebuild and deploy again

Note: This is also applicable for *non maven* project.

#### ���jstl

```xml
 <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
```

#### maven Դ��� ��������

- �޸�Eclipse���ı��ļ���Ĭ�ϱ��룺windows->Preferences->general->Workspace->Text file encoding����ΪUTF-8
- �޸�JAVAԴ�ļ���Ĭ�ϱ��룺windows->Preferences->general->Content Types->�Ҳ�Context Types�����㿪Text��ѡ��Java Source File���������Default encoding�����������UTF-8����Update��
- ����������룬�ǵ�����eclipse ��

�ο��� https://blog.csdn.net/zsw12013/article/details/51502876