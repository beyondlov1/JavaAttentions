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