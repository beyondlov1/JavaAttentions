### 安装
下载release包, 解压
windows 下修改bat文件, 将logback 设置前边的@rem去掉
linux 下为异步启动, 查看有没有成功需要看log文件

### client
1.1.4版本还只支持一个client对应一个server(或者是一组server)
client的集群只有一个启用, 其他的作为冷备
client的集群是在选择用clusterConnector的时候就启用的, 这个不仅会连接server的集群
而且会用同一个zookeeper去管理client
在一个client挂掉之后会启用另一个client, 中间会有延迟

### kafka
直接投递到kafka时要注意投递的flatMessage是不是等于true
如果为true则kafkaclient要用getFlatListWithoutAck方法
如果为false则用getListWithoutAck


### 指定位置同步
1. 
修改
canal.instance.master.journal.name=mysql-bin.000006
canal.instance.master.position=202
canal.instance.master.timestamp=
删除 meta.dat 文件
2.
直接修改 meta.dat 文件
