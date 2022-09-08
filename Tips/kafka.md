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

### exactly once
https://cloud.tencent.com/developer/article/1805392#:~:text=%E5%9C%A8%E4%BD%BF%E7%94%A8kafka,%E4%BB%AC%E4%BB%8B%E7%BB%8D%E5%A6%82%E4%BD%95%E5%AE%9E%E7%8E%B0%E3%80%82

