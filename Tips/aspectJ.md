### 各种execution
普通
execution(返回值 com.beyond.service.*.*(..))
寻找方法上有注解的:
execution(@com.beyond.commute.permission.RequirePermission * *(..))

详细的见下边这个网址,如果打不开去 onenote/technology/JAVA/Spring中找:
http://blog.espenberntsen.net/2010/03/20/aspectj-cheat-sheet/
https://www.cnblogs.com/weizhxa/p/8567942.html


### aspectj 和 lombok 不兼容
解决方法:
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
	
	
	
	idea 在 classpath 下检测到 aspect 的包之后, 会选择 ajc 的编译器, 而 lombok 不支持ajc, 只支持 javac, EJC.
	所以上边的解决思路就是, 用delombok 去生成java文件, 然后用 ajc 进行编译
	
	但是, 这种方法运行的时候只能用 spring boot 的maven插件运行, 而且, 生成的java文件会在编译器里提示重复class, 单元测试也比较麻烦