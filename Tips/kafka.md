### 外网访问不到
server.conf 配置listeners
https://blog.csdn.net/qq_35688140/article/details/100010905


### 命令
启动server
bin\windows\kafka-server-start.bat config\server.properties

producer
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic example

consumer
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic example --from-beginning

zookeeper
bin\windows\zookeeper-server-start.bat config\zookeeper.properties