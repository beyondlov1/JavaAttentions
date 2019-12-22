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

