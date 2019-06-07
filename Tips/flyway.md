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
baselineOnMigrate这个属性是如果启动时没有这个表， 就在数据新建这个表， 并且以