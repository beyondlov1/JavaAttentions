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


### 回退（未验证）
https://bbs.csdn.net/topics/391815336

demo: spring-boot-test

### android+flyway 超级大坑
gradle: dependency
```
 implementation 'org.flywaydb:flyway-core:3.0'
    implementation 'org.sqldroid:sqldroid:1.1.0-rc1'
```
ps: 这里要用3.0, 因为最新版本会报setEscapeProcessing not implemented
android 的 json 下添加：
```

    sourceSets {
        // Place your db/migration folder here
        main { assets.srcDirs = ['src/main/assets'] }
    }
```
java: 
```
        DroidDataSource dataSource = new DroidDataSource(getPackageName(), "databases/beyond_not_safe_flyway_test");
ContextHolder.setContext(this);
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setInitOnMigrate(true);

        flyway.migrate();
```
这是3.0的写法

大坑：5.2.4 和 6.0.0-beta2 :
新的采用链式编程， 在本机测试不支持android sqlite, 原因可能是判断sqlite版本错（源码中貌似是获取的sqlite数据库的version， 但是这个version是数据库更新（alter...）的版本， 不是数据库的版本, 这是个bug。
不过通过反射的方式可以暂时更改数据库更新版本：
```
        //添加在创建datasource后边
        try {
            DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
            Class<? extends DatabaseMetaData> aClass = metaData.getClass();
            Field con = aClass.getDeclaredField("con");
            con.setAccessible(true);
            SQLDroidConnection connection = (SQLDroidConnection)con.get(metaData);
            connection.getDb().getSqliteDatabase().setVersion(3);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
```
但是：这种方法有两个问题
1. 这种方法会更改数据库的更新版本， 由于更新版本不能降级， 所以可能会出现问题，比如：greendao会默认将更新版本设置为1, 所以就会报错
2. 在