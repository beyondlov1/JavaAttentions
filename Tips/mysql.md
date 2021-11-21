### 从外部访问电脑的mysql报错
java.sql.SQLException: null, message from server: "Host 'XXX' is not allowed to connect
原因 权限问题
参考: https://blog.csdn.net/m0_37460012/article/details/90290148


### 查看binlog format
 show variables like 'binlog_format';

 ### 开启binlog

 my.ini修改:
 [mysqld]
log-bin=mysql-bin # 开启 binlog
binlog-format=ROW # 选择 ROW 模式
server_id=1 # 配置 MySQL replaction 需要定义，不要和 canal 的 slaveId 重复

### 主从同步
主:
	C:\ProgramData\MySQL\MySQL Server 5.6\my.ini:
	[mysqld]
	server-id=1
	log-bin=mysql-bin
	binlog_format=MIXED
	binlog-do-db = db_beyond_test
从:
	C:\ProgramData\MySQL\MySQL Server 5.6\my.ini:
	[mysqld]
	server-id=2
	log-bin=mysql-bin
	binlog_format=ROW
	binlog-do-db = db_beyond_test
	log-slave-updates=1 //从库作为其他从库的主库时添加
	
	执行:
	change master to master_host='192.168.102.31', master_user='repl', master_password='123456',master_port=3306, master_log_file='mysql-bin.000001', master_log_pos=514;
	start slave;
	如果一开始同步不了, 可以查看show master status;会有报错信息
	首次同步不了应该是没有创建相关数据库和相关表
	外部内网ip连接不了数据库时添加(https://www.cnblogs.com/anlia/p/9321535.html):
	update user set host = '%' where user = 'root';
	flush privileges;
	查看相关命令:
	show variables like 'binlog_format';
	show master status;
	show slave status;

### 查看binlog
show binary logs;  
show binlog events in 'mysql-bin.000047';  

参考: https://blog.csdn.net/silentwolfyh/article/details/82684203

### 在线修改 binlog_format
stop slave;  
set global binlog_format='ROW';  
start slave;  

### binlog文件与format
修改format不会添加mysql-bin的log文件, 而是会继续写, 靠 EvenType 来区分

### mysql查询优化
https://www.mysqlzh.com/doc/66/627.html


### mycat 两阶段提交 分布式事务
https://www.cnblogs.com/libin6505/p/11290141.html
https://cloud.tencent.com/developer/article/1643274
https://www.cnblogs.com/cxxjohnson/p/9145548.html

### mysql proxy
https://downloads.mysql.com/archives/proxy/
https://cloud.tencent.com/developer/article/1047570

有hook, lua脚本