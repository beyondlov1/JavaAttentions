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