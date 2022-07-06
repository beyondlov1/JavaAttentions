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

### mysql 锁表 (亲测 mysql5.6)
如果根据索引查询, 没有该数据, 则不会锁表, 其他正常查询
如果不根据索引, 不管有没有该数据, 都会锁表
参考: https://blog.csdn.net/hardplay123/article/details/88774240


### timestamp update on change 
数据不改变时, 时间戳不会更改, 即使执行了update操作


MRR multi range read 
ICP index condition pushdown

### keys
- 存储结构: 行 compact 页 区 段 redo undo leafnode non-leafnode
- 索引结构: 二级索引结构
- MVVC readview trx_id
- redo 512字节, 不用doublewrite LSN  checkout flush currLSN
- double write 把脏页memcpy 到doublewritebuffer, 然后写入磁盘, 调用fsync, 然后刷入脏页
  解决问题: 防止脏页部分写, 要保证page要么都写进去, 要么就从doublewritebuffer的文件恢复
  类似于先写日志, 再慢慢写
- undo 链 提交日志时判断undopage是否可复用, 判断是否要删除(insert)
  purge 离散读取, 一个事务的undo可能分布与不同undopage中, purge比较慢, 在master thread中处理
  按页purge, 如果某个undolog被某个事务引用, 则换一个事务开始追踪清理
- binlog, mysql层, 逻辑日志, 使用两段式提交, 保证binlog 和redolog中的内容相同, 避免binlog写入了, 但是redolog没写成功
- group commit , 多个commit一起提交, 但是有了binlog后, 为了保证顺序所以要加mutex锁,导致性能下降, 之后用队列, Flush Sync Commit 三个阶段解决, 队列中的一起刷盘
- latch(多线程) lock(数据库中的锁)   X S IX IS , 位图存储每一页中被锁定的行, 也能锁索引
  READ REPEATABLE 间隙锁 next-key锁 Record锁
- 死锁检测机制  timeout wait-for graph
- 空闲列表 FREE FULL NOT_FULL      Fragment
- xtrabackup 利用LSN来寻找有增量的页
