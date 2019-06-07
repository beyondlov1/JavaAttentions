### flyway
flyway 是一个数据库版本工具，
原理是在数据库中建一个表来保存脚本执行信息
### spring-boot flyway
配置文件：
```

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306
spring.datasource.username=sa
spring.datasource.password=

spring.flyway.baselineOnMigrate=true
spring.flyway.schemas=test
```
baselineOnMigrate这个属性是如果启动时没有这个表， 就在数据新建这个表， 并且以现在的状态作为基线， ps： 这里会把版本设置V1， 所以文件以V1开头的脚本并不会执行
schemas这个指定默认的schema， 也是表要建到的地方

### 脚本文件格式
V1__XXXX.sql
V1.1___XXX.sql
or V2__XXX.sql

### java 代码
```
   // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://localhost:3306/test", "sa", "").load();

        // Start the migration
        flyway.migrate();

```
### 默认脚本地址
resourse/db/migration
可由配置修改
https://www.cnblogs.com/harrychinese/p/springboot_flyway.html

###  Migration checksum mismatch for migration version 1.3
解决办法直接改表， 把对应的数据删掉或者修改

https://blog.csdn.net/weigeshikebi/article/details/80504895


### 回退
