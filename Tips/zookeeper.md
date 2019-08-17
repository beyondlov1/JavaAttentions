### 安装

1. ZOOKEEPER_HOME
2. 修改conf, zoo.cfg
3. 启动 zkServer
4. 启动 zkCli -server localhost:2181 连接

### 伪集群启动

1. 创建多个配置文件

   ```
   # The number of milliseconds of each tick
   tickTime=2000
   # The number of ticks that the initial 
   # synchronization phase can take
   initLimit=10
   # The number of ticks that can pass between 
   # sending a request and getting an acknowledgement
   syncLimit=5
   # the directory where the snapshot is stored.
   # do not use /tmp for storage, /tmp here is just 
   # example sakes.
   dataDir=D:/software/zookeeper/apache-zookeeper-3.5.5-bin/apache-zookeeper-3.5.5-bin/dataDir1
   # the port at which the clients will connect
   clientPort=2181
   # the maximum number of client connections.
   # increase this if you need to handle more clients
   #maxClientCnxns=60
   #
   # Be sure to read the maintenance section of the 
   # administrator guide before turning on autopurge.
   #
   # http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
   #
   # The number of snapshots to retain in dataDir
   #autopurge.snapRetainCount=3
   # Purge task interval in hours
   # Set to "0" to disable auto purge feature
   #autopurge.purgeInterval=1
   
   
   server.1=localhost:2887:3887
   server.2=localhost:2888:3888
   server.3=localhost:2889:3889
   ```

2. 创建 dataDir1,2,3

3. 修改bat 文件, 添加 

   ```
   set ZOOCFG=D:\software\zookeeper\apache-zookeeper-3.5.5-bin\apache-zookeeper-3.5.5-bin\conf\zoo1.cfg
   
   ```

4. dataDir 中添加 myid 文件
    里面写 编号  也就是 server.xxx 中的 xxx

5. 这里要注意: config 文件中 dataDir 要用 / 不要用 \

### 查看server 状态

- 默认会启动一个 adminServer, 默认地址: localhost:8080
- 可在zoo.conf 中修改: 添加 admin.serverPort=8083
- 然后访问: http://localhost:8083/commands/monitor

这个可能也有用, 但是没试过: https://blog.csdn.net/x763795151/article/details/80599498

<https://www.cnblogs.com/qlqwjy/p/10491456.html> 

<https://blog.csdn.net/qq_36148847/article/details/80114283> 