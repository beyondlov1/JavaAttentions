#### Mysql 安装

1. 下载MySql 5.5版本， 下载zip版本
2. 解压
3. cmd 到解压的文件目录/bin
4. 执行 mysqld install(如果是用admin方式启动的cmd，可能需要.\mysqld install)
5. 之后 net start mysqld
6. 修改root密码：进入mysql>set password for root@localhost = password(‘123’);



#### mysql 5.5.60 中文乱码问题

1. 复制一个my-xxx.ini文件改名为my.ini

2. 在对应位置加入如下代码

   ```
   [client]
   default-character-set=utf8
   
   [mysqld]
   character-set-server=utf8  
   collation-server=utf8_general_ci
   init_connect = 'SET NAMES utf8'
   
   [mysql]
   character-set-server=utf8 (不能加)
   ```

3. 改过的文件在百度网盘/mysql配置文件