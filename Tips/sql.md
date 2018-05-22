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

 