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


### mybatis foreach

foreach一共有三种类型，分别为List,[](array),Map三种。

foreach的第一篇用来将List和数组(array)。



下面表格是我总结的各个属性的用途和注意点。

foreach属性
属性	描述
item	循环体中的具体对象。支持属性的点路径访问，如item.age,item.info.details。
具体说明：在list和数组中是其中的对象，在map中是value。
该参数为必选。
collection	要做foreach的对象，作为入参时，List<?>对象默认用list代替作为键，数组对象有array代替作为键，Map对象没有默认的键。
当然在作为入参时可以使用@Param("keyName")来设置键，设置keyName后，list,array将会失效。 除了入参这种情况外，还有一种作为参数对象的某个字段的时候。举个例子：
如果User有属性List ids。入参是User对象，那么这个collection = "ids"
如果User有属性Ids ids;其中Ids是个对象，Ids有个属性List id;入参是User对象，那么collection = "ids.id"
上面只是举例，具体collection等于什么，就看你想对那个元素做循环。
该参数为必选。
separator	元素之间的分隔符，例如在in()的时候，separator=","会自动在元素中间用“,“隔开，避免手动输入逗号导致sql错误，如in(1,2,)这样。该参数可选。
open	foreach代码的开始符号，一般是(和close=")"合用。常用在in(),values()时。该参数可选。
close	foreach代码的关闭符号，一般是)和open="("合用。常用在in(),values()时。该参数可选。
index	在list和数组中,index是元素的序号，在map中，index是元素的key，该参数可选。

参考: https://blog.csdn.net/isea533/article/details/21237175

### 数据类型
tinyint(M) 这个M代表只是展示的位数（在选中是否填充0的情况下有用）
tinyint(1) 和 boolean 同义词

### MySQL按天/月统计
<!-- 按日查询 -->  
SELECT DATE_FORMAT(created_date,'%Y-%m-%d') as time,sum(money) money FROM o_finance_detail where org_id = 1000  GROUP BY  time  
<!-- 按月查询 -->  
SELECT DATE_FORMAT(created_date,'%Y-%m') as time,sum(money)  money FROM o_finance_detail where org_id = 1000  GROUP BY  time  
<!-- 按年查询 -->  
SELECT DATE_FORMAT(created_date,'%Y') as time,sum(money)  money FROM o_finance_detail where org_id = 1000  GROUP BY  time   
<!-- 按周查询 -->  
SELECT DATE_FORMAT(created_date,'%Y-%u') as time,sum(money)  money FROM o_finance_detail where org_id = 1000  GROUP BY  time

https://www.cnblogs.com/shaoing/p/8971758.html
### MySQL 时间戳和日期的相互转换
1.日期转时间戳
select UNIX_TIMESTAMP('2018-12-25 12:25:00');
结果：1545711900

2.时间戳转日期：FROM_UNIXTIME(unix_timestamp) --unix_timestamp为时间戳
select FROM_UNIXTIME(1545711900);
结果：2018-12-25 12:25:00

3.时间戳转日期，自定义返回日期格式：FROM_UNIXTIME(unix_timestamp,format) -- format请参考后面的截图
select FROM_UNIXTIME(1545711900,'%Y-%m-%d %T');
-- 结果：2018-12-25 12:25:00
https://blog.csdn.net/qq_25112523/article/details/85251808

### MySQL 书籍
https://www.cnblogs.com/prettyisshit/p/5841055.html

### in / exists

**如果查询的两个表大小相当，那么用in和exists差别不大**。 

如果两个表中一个较小，一个是大表，则子查询表大的用exists，子查询表小的用in： 

例如：表A（小表），表B（大表）

1：

select * from A where cc in (select cc from B) 效率低，用到了A表上cc列的索引；

select * from A where exists(select cc from B where cc=A.cc) 效率高，用到了B表上cc列的索引。 

相反的

2：

select * from B where cc in (select cc from A) 效率高，用到了B表上cc列的索引；

select * from B where exists(select cc from A where cc=B.cc) 效率低，用到了A表上cc列的索引。

 

not in 和not exists如果查询语句使用了not in 那么内外表都进行全表扫描，没有用到索引；而not extsts 的子查询依然能用到表上的索引。**所以无论那个表大，用not exists都比not in要快**。 

in 与 =的区别 

select name from student where name in ('zhang','wang','li','zhao'); 

与 

select name from student where name='zhang' or name='li' or name='wang' or name='zhao' 

的结果是相同的。

参考(值得点开)：https://www.cnblogs.com/beijingstruggle/p/5885137.html

### 优化

https://www.cnblogs.com/xupccc/p/9661972.html

查询模式

联表时第一个会把where条件中自己可查的and 的where条件加上, 筛选出第一批

之后用筛选出的去根据第二个表的查询条件筛选出第二批

以此类推.... (nested loop)



非关联子查询

子查询中不需要父查询传入数据

关联子查询

子查询中需要父查询传入数据, 外边的查询一次, 子查询就执行一次(尽量不要用, 有OR和LIKE的时候会奇慢无比, 暂未找到原因)


### Transaction VS for update
for update 可以锁住一行, 但是要加上事务才会起作用
示例:Demos/spring-boot-playground/com.beyond.transaction