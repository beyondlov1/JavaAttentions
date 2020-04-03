### windows 安装

https://www.jianshu.com/p/faf038923093

https://dev.to/awwsmm/installing-and-running-hadoop-and-spark-on-windows-33kc

https://blog.csdn.net/r_aider/article/details/80076779

https://www.jianshu.com/p/0d4a365ef350

https://github.com/MuhammadBilalYar/Hadoop-On-Window/wiki/Step-by-step-Hadoop-2.8.0-installation-on-Window-10

https://blog.csdn.net/r_aider/article/details/80076779

具体参考demo: hadoop-conf-demo

注意: 如果本机装过 yarn (npm) , 要么卸载, 要么改变环境变量顺序

start-all.cmd 要 管理员权限打开

默认 tmp 文件实在 c 盘根目录创建的



服务都起了, 但是实例运行不了:

搞不懂为什么....



### linux 安装

- 安装虚拟机

- 联网 : https://www.jianshu.com/p/0d4a365ef350

- 装 jdk , Oracle 的,

- 设置 JAVA_HOME:  rpm 安装 默认路径 /usr/java/jdk1.8.0-.....   https://blog.csdn.net/qq_32786873/article/details/78749384

- source /etc/profile  刷新

- 启动例子:  

 ```
  bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.1.2.jar wordcount /root/hadoop/hadoop-data/wc.input output
 ```
- output 会创建一个文件夹, 位置是在执行命令时候的相对位置

参考: https://www.jianshu.com/p/0d4a365ef350

https://blog.csdn.net/wind520/article/details/9308809

https://www.cnblogs.com/stulzq/p/9286878.html



#### 伪集群安装

https://www.jianshu.com/p/0d4a365ef350

https://www.jianshu.com/p/99f3b52a5c54

如果出现 XXX_XXX_USER 什么之类的错误: https://stackoverflow.com/questions/48129029/hdfs-namenode-user-hdfs-datanode-user-hdfs-secondarynamenode-user-not-defined

如果出现 Permission xxxx 的错误 : https://www.cnblogs.com/lanxuezaipiao/p/3525554.html



解决运行 Hadoop MapReduce 任务时错误: 找不到或无法加载主类 :
https://blog.sunriseydy.top/technology/big-data/hadoop/cannotfind-mrappmaster/

虚拟机运行可能会出现 143 的错误, 为内存不足的错误, 调到 2G 就可以了.



### windows 访问 虚拟机web

https://blog.csdn.net/ytangdigl/article/details/79796961

关闭防火墙即可


### 官方在维护两个版本, 要注意!!!

### but there is no HDFS_SECONDARYNAMENODE_USER defined. Aborting operation.
export HDFS_NAMENODE_USER="root"
export HDFS_DATANODE_USER="root"
export HDFS_SECONDARYNAMENODE_USER="root"
export YARN_RESOURCEMANAGER_USER="root"
export YARN_NODEMANAGER_USER="root"
https://stackoverflow.com/questions/48129029/hdfs-namenode-user-hdfs-datanode-user-hdfs-secondarynamenode-user-not-defined

### 理解
hadoop 是个分布式存储系统
https://blog.csdn.net/w12345_ww/article/details/51910889


### 修改hostname
 hostnamectl set-hostname centos77.magedu.com             # 使用这个命令会立即生效且重启也生效
https://www.cnblogs.com/zhaojiedi1992/p/zhaojiedi_linux_043_hostname.html

### 执行mapReduce 找不到类
https://blog.csdn.net/hongxiao2016/article/details/88919176


### linux 查看端口占用
lsof -i:port
如果没有命令
yum install lsof


### mapReduce 适用范围
但是，Map/Reduce并不是万能的，适用于Map/Reduce计算有先提条件：
（1）待处理的数据集可以分解成许多小的数据集；
（2）而且每一个小数据集都可以完全并行地进行处理；
若不满足以上两条中的任意一条，则不适合适用Map/Reduce模式。


### Type mismatch in key from map: expected org.apache.hadoop.io.LongWritable, received org.apache.hadoop.io.Text
job.setMapOutputKeyClass(Text.class);
job.setMapOutputValueClass(IntWritable.class);

### 免密登陆其他机器
master机器:
```
ssh-keygen -t rsa # 不要更改默认名
scp /root/.ssh/id_rsa.pub root@slave:~/.ssh  # 注意不要覆盖
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

``` 
slave机器: 
```
cat ~/.ssh/传过来的文件名.pub >> ~/.ssh/authorized_keys
```

### LoggerFactory is not a Logback LoggerContext but Logback is on the classpath. Either remove Logback

<dependency>
            <groupId>org.apache.hbase</groupId>
            <artifactId>hbase-client</artifactId>
            <version>2.2.3</version>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
		
https://blog.csdn.net/Dongguabai/article/details/82966440


### 免密
客户端: 
```
ssh-keygen
```
一路回车
复制 id_rsa.pub 中的内容到服务端的 ~/.ssh/authorized_keys 中


windows的操作也是一样的, 不同的bash客户端要单独生成

```
ssh-keygen
scp ~/.ssh/id_rsa.pub root@server:~/.ssh/id_rsa.pub
ssh root@server
cat ~/.ssh/id_rsa.pub >> authorized_keys
```


### idea 用bash的控制台
Terminal 插件 , 设置, 把cmd.ext 改成 git bash 的路径