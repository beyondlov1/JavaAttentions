### head 安装
https://blog.csdn.net/sqlaowen/article/details/81735441 
一开始连不上是因为跨域问题
要在elasticsearch.yml中添加
http.cors.enabled: true
http.cors.allow-origin: "*"



### 部署

##### vm 部署es, 要让外边可以访问

1. 要在 elasticsearch.yml 中添加

network.host: 192.168.1.128   // 本机地址或者0.0.0.0
bootstrap.memory_lock: false
bootstrap.system_call_filter: false
cluster.initial_master_nodes: ["node-1"]

(不确定上边的都需要)


下面的都必要


2. 加上上边的配置之后启动会报错, (可打开文件数过少, 可用线程数过少)

   解决:

   修改/etc/security/limits.conf文件

```shell
*               soft    nofile          65536
*               hard    nofile          65536
*               soft    nproc           4096
*               hard    nproc           4096
```

要复制, 中间的空格不能删减

https://www.cnblogs.com/zhi-leaf/p/8484337.html

###### 集群部署
修改配置文件:
master:

cluster.name: elasticsearch-beyond
node.name: node-1
http.cors.enabled: true
http.cors.allow-origin: "*"
network.host: 192.168.1.128
bootstrap.memory_lock: false
bootstrap.system_call_filter: false
cluster.initial_master_nodes: ["node-1"]
node.master: true
discovery.zen.ping_timeout: 80s
discovery.zen.ping.unicast.hosts: ["192.168.1.128:9300","192.168.1.129:9300"]

data:

cluster.name: elasticsearch-beyond
node.name: node-2
http.cors.enabled: true
http.cors.allow-origin: "*"
network.host: 192.168.1.129
bootstrap.memory_lock: false
bootstrap.system_call_filter: false
cluster.initial_master_nodes: ["node-1"]
node.master: false
discovery.zen.ping_timeout: 80s
discovery.zen.ping.unicast.hosts: ["192.168.1.128:9300","192.168.1.129:9300"]

PS:
如果因为全复制的, 可能会报下边的错误
搭建elsticsearch集群 报错with the same id but is a different node instance解决办法
解决方法: 清空data下的文件
https://blog.csdn.net/qq_24879495/article/details/77718032


### join VS nested
**nested 和 parent-child的区别以及使用场景**

    主要区别：
    由于存储结构的不同，nested和parent-child的方式有不同的应用场景
    nested 所有实体存储在同一个文档，parent-child模式，子type和父type存储在不同的文档里。
    所以查询效率上nested要高于parent-child，但是更新的时候nested模式下，es会删除整个文档再创建，而parent-child只会删除你更新的文档在重新创建，不影响其他文档。所以更新效率上parent-child要高于nested。

    使用场景：
    nested：在少量子文档，并且不会经常改变的情况下使用。
    比如：订单里面的产品，一个订单不可能会有成千上万个不同的产品，一般不会很多，并且一旦下单后，下单的产品是不可更新的。
    parent-child：在大量文档，并且会经常发生改变的情况下使用。
    比如：用户的浏览记录，浏览记录会很大，并且会频繁更新
https://blog.csdn.net/tuposky/article/details/80988915#t3


### logstash 导入报错

[2020-01-05T13:03:46,036][ERROR][logstash.outputs.elasticsearch][.monitoring-logstash] Encountered a retryable error. Will Retry with exponential backoff  {:code=>500, :url=>"http://192.168.0.44:9200/_monitoring/bulk?system_id=logstash&system_api_version=7&interval=1s"}
[2020-01-05T13:16:04,892][ERROR][logstash.outputs.elasticsearch][.monitoring-logstash] Encountered a retryable error. Will Retry with exponential backoff  {:code=>500, :url=>"http://192.168.0.44:9200/_monitoring/bulk?system_id=logstash&system_api_version=7&interval=1s"}
[2020-01-05T13:19:00,856][WARN ][logstash.outputs.elasticsearch][.monitoring-logstash] Restored connection to ES instance {:url=>"http://192.168.0.42:9200/"}
[2020-01-05T13:25:54,426][WARN ][logstash.outputs.elasticsearch][.monitoring-logstash] Restored connection to ES instance {:url=>"http://192.168.0.42:9200/"}
[2020-01-05T13:27:16,646][WARN ][logstash.outputs.elasticsearch][.monitoring-logstash] Restored connection to ES instance {:url=>"http://192.168.0.42:9200/"}
[2020-01-05T13:31:08,822][WARN ][logstash.outputs.elasticsearch][.monitoring-logstash] Restored connection to ES instance {:url=>"http://192.168.0.42:9200/"}

这里报错是因为批量导入时的发送的数据太大了
这里伴随着es也会报一个错, 是因为有一个字段特别长, 导致超过了限制
ps: 这里logstash不会自动停止, 而是会重试

this seems to be caused by ES rejecting a bulk request due to it being larger than http.max_content_length after being uncompressed. currently logstash checks for the size of the bulk request after compression, which means that a heavily compressed bulk request of 200kb can carry 200mb of data, causing a 413.
This plugin needs to be changed to perform the size check before compression
https://github.com/logstash-plugins/logstash-output-elasticsearch/issues/823
解决办法: 去除那么长的字段, 或者, 去除keyword的子字段

### es手动移动分片
#动态设置es索引副本数量
curl -XPUT 'http://168.7.1.67:9200/log4j-emobilelog/_settings' -d '{
   "number_of_replicas" : 2
}'
 
#设置es不自动分配分片
curl -XPUT 'http://168.7.1.67:9200/log4j-emobilelog/_settings' -d '{
   "cluster.routing.allocation.disable_allocation" : true
}'
 
#手动移动分片
curl -XPOST "http://168.7.1.67:9200/_cluster/reroute' -d  '{
   "commands" : [{
		"move" : {
			"index" : "log4j-emobilelog",
			"shard" : 0,
			"from_node" : "es-0",
			"to_node" : "es-3"
		}
	}]
}'
#手动分配分片
curl -XPOST "http://168.7.1.67:9200/_cluster/reroute' -d  '{
   "commands" : [{
		"allocate" : {
			"index" : ".kibana",
			"shard" : 0,
			"node" : "es-2",
		}
	}]
}'
