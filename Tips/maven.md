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
