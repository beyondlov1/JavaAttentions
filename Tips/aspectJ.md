### ����execution
��ͨ
execution(����ֵ com.beyond.service.*.*(..))
Ѱ�ҷ�������ע���:
execution(@com.beyond.commute.permission.RequirePermission * *(..))

��ϸ�ļ��±������ַ,����򲻿�ȥ onenote/technology/JAVA/Spring����:
http://blog.espenberntsen.net/2010/03/20/aspectj-cheat-sheet/
https://www.cnblogs.com/weizhxa/p/8567942.html


### aspectj �� lombok ������
�������:
 <build>
        <sourceDirectory>${project.build.directory}/generated-sources/delombok</sourceDirectory>
        <plugins>
            <plugin>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-maven-plugin</artifactId>
                <version>1.16.16.0</version>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>delombok</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <addOutputDirectory>false</addOutputDirectory>
                    <sourceDirectory>src/main/java</sourceDirectory>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>aspectj-maven-plugin</artifactId>
                <version>1.8</version>
                <configuration>
                    <complianceLevel>1.8</complianceLevel>
                    <source>1.8</source>
                    <target>1.8</target>
                    <outxml>true</outxml>
                    <verbose>true</verbose>
                    <showWeaveInfo>true</showWeaveInfo>
                    <aspectLibraries>
                        <aspectLibrary>
                            <groupId>org.springframework</groupId>
                            <artifactId>spring-aspects</artifactId>
                        </aspectLibrary>
                    </aspectLibraries>
                </configuration>
                <executions>
                    <execution>
                        <phase>process-classes</phase>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
	
	
	
	idea �� classpath �¼�⵽ aspect �İ�֮��, ��ѡ�� ajc �ı�����, �� lombok ��֧��ajc, ֻ֧�� javac, EJC.
	�����ϱߵĽ��˼·����, ��delombok ȥ����java�ļ�, Ȼ���� ajc ���б���
	
	����, ���ַ������е�ʱ��ֻ���� spring boot ��maven�������, ����, ���ɵ�java�ļ����ڱ���������ʾ�ظ�class, ��Ԫ����Ҳ�Ƚ��鷳