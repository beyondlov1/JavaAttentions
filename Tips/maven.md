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
