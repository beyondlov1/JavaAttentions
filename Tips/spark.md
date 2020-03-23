### 安装
安装 java
安装 scala  .tgz 的解压就可以
安装 spark 安装without-hadoop的就可以

如果用hadooop的:
spark-env.sh 中添加:
```
export SPARK_DIST_CLASSPATH=/opt/hadoop/hadoop-3.2.1/etc/hadoop:/opt/hadoop/hadoop-3.2.1/share/hadoop/common/lib/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/common/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/hdfs:/opt/hadoop/hadoop-3.2.1/share/hadoop/hdfs/lib/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/hdfs/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/mapreduce/lib/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/mapreduce/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/yarn:/opt/hadoop/hadoop-3.2.1/share/hadoop/yarn/lib/*:/opt/hadoop/hadoop-3.2.1/share/hadoop/yarn/* 
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk
export SCALA_HOME=/opt/scala/scala-2.13.1
export SPARK_MASTER=192.168.1.128
export SPARK_WORKER_MEMORY=256m
export HADOOP_HOME=/opt/hadoop/hadoop-3.2.1
```

参考: https://www.jianshu.com/p/09143312dd94

开启之后 master: jps: 多出 MASTER WORKER
slave: jps: 多出 WORKER

### web
http://master:8080

job: 开启shell才有
http://master:4040
