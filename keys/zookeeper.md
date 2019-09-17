### zookeeper工作原理
zookeeper保存并管理服务关心的数据，并能够在数据发生变化时通知订阅者
1. 服务器启动， 在zookeeper中注册，创建一个节点
2. client去获取列表， 并且监听
3. 服务器下线，触发zookeeper中node的变化， 触发监听事件
### zookeeper集群内部结构
leader - follower
ZAB算法
### 选举算法
每个集群节点先选择自己， 如果还没达到半数以上就不能成为leader，当其他机器进入集群， 选择myid比较大的， 如果myid比较大的得到了半数以上的投票，则选举为leader
此时其他节点再进来就成为了follower
### 节点类型
临时节点
临时序号节点
持久化节点
持久化序号节点
### 监听器原理
在client启动时会创建两个线程， 
一个是connect线程， 用来传递数据
另一个是listener线程，用来监听事件

启动时，connect会调用getChildren（“/",true)的方法告诉zookeeper 监听的ip和端口号， 并且放到注册列表中。 如果有事件发生， 向监听的端口发送事件
