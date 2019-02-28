**timestamp转化为-秒-**

unix_timestamp(timestamp_column)

---

**oracle数据库jdbc的where语句问题**

**问题:** 当某一字段(如id)为char或者varchar2类型时, 用where id="..."的preparedStatement不能获得数据

**解决办法:** 改成varchar类型

---

**MySQL软件yog密码错误:2058错误**


错误新版的 mysql 8.0.11 安装。下载了sqlyog工具, 连接 mysql
配置新连接报错：错误号码 2058，分析是 mysql 密码加密方法变了。
解决方法：windows 下cmd 登录 mysql -u root -p 登录你的 mysql 数据库，然后 执行这条SQL：
 `ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';`

password 是你自己设置的root密码
然后在重新配置SQLyog的连接，则可连接成功了，OK。 



---

**autoReconnect**

错误: Caused by: com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: The last packet successfully received from the server was 31,650,305 milliseconds ago.  The last packet sent successfully to the server was 31,650,305 milliseconds ago. is longer than the server configured value of 'wait_timeout'. You should consider either expiring and/or testing connection validity before use in your application, increasing the server configured values for client timeouts, or using the Connector/J connection property 'autoReconnect=true' to avoid this problem.

解决方法: 如果连接闲置8小时 (8小时内没有进行数据库操作), mysql就会自动断开连接, 要重启tomcat. 
    不用hibernate的话, connection url加参数: autoReconnect=true 
    用hibernate的话, 加如下属性: 
        <property name="connection.autoReconnect">true</property> 
        <property name="connection.autoReconnectForPools">true</property> 
        <property name="connection.is-connection-validation-required">true</property> 
    要是还用c3p0连接池: 
        <property name="hibernate.c3p0.acquire_increment">1</property> 
        <property name="hibernate.c3p0.idle_test_period">0</property> 
        <property name="hibernate.c3p0.timeout">0</property> 
        <property name="hibernate.c3p0.validate">true</property>

---

**mysql 添加[取消]timestamp的自动更新**

MySQL 默认timestamp自动更新, 要用下面的方法取消
alter table hello change uptime uptime timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP;
https://blog.csdn.net/rongge2008/article/details/50116457

---

**SQL查询选修了所有课程的学生姓名**

select sname
from student
where not exists
(select *
from course
where not exists  
(select *
from sc
where sno =student.sno
and cno=course.cno);



#### 时间相关

- 从时间获取日期， 精确到日： select 当前日期=convert(varchar(10),getdate(),120) 

参考：https://www.cnblogs.com/nyzhai/p/3594015.html

https://www.cnblogs.com/whl4835349/p/5889298.html

#### sqlLite时间

DATE(LAST_MODIFY_TIME/1000,'unixepoch','localtime') // greenDao存的日期是到毫秒值的， 所以用date函数的时候要除以1000
http://www.compileonline.com/execute_sql_online.php
https://www.cnblogs.com/xmphoenix/archive/2011/05/23/2054022.html
