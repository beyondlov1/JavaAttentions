### windows 配置文件

windows 下的配置文件默认为 redis.windows-service.conf 

修改方法: https://blog.csdn.net/yang5726685/article/details/81212770

设置还可以在cli 中直接设置:  config set notify-keyspace-events KEA

### keyspace 通知

http://redisdoc.com/topic/notification.html

### spring data redis

- redisTemplate
- 事务 multi exec discard : 注意, 只读的操作不能放到multi和exec中间,  会报空指针, 因为它会另给起一个线程
- demo redis-chat-root-demo