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

### keys
- sds len free buf 扩 2倍 缩
- list len head tail dup() free() match() ... listNode
- dict     hashtable    dictEntry **table next链表     数组+链表
    - rehash 两个数组  rehashidx -> 正在进行rehash的index,  完成后变 -1
        - 找不到去另一个数组找
        - 写入都写入到新的
- zskiplist score obj  level next
- intset
    - encoding length content[] 每个数字占用字节相同
    - 升级
    - 级联
- ziplist
    - zlbytes zltail zllen entry1 entry2 ... zlend
    - entry: previous_entry_lenght encoding content
    - 连锁更新, 小概率, 只有都接近 255 的时候才会发生, 否则就会在中间断掉

- redis obj type(5种) encoding(对应使用的数据结构) ptr
    - string 
        - int
        - embstr
        - raw
    - list
        - ziplist
        - linkedlist
    - set
        - intset
        - hashtable
    - zset
        - ziplist
        - skiplist
    - hash
        - ziplist
        - hashtable
- 类型检查   检查type
- 命令多态   根据encoding调用不同api
- 共享对象   引用计数器
- 过期
    - 保存: 专门的dict: key-指向键obj的指针 value-时间
    - 删除策略: 惰性删除 定期删除(规定时间内多次遍历)

- 持久化
    - rdb
        - 子进程
        - dirty计数器 上次save开始算
    - aof
        - 结束文件时间之前, 调用 flushAppendOnlyFile
            - always(tofile and fsync) everysec(tofile and fsync) no(tofile)
        - aof重写: 直接用内存中的数据重写, 和原来的aof文件没关系
- 事件
    - 文件事件
    - 时间事件
    - 文件事件之后会顺便执行时间事件, 所以文件事件要有个timeout
- client
    - flags buf ...
- 复制 PSYNC
    - 全量 RDB
    - 增量 积压缓冲区 + offset 
    - 命令传播
    - 心跳
- sentinel
    - channel+命令
    - sentinel 也是 redis 服务器
    - 主观下线  客观下线
    - 故障转移
    - sentinel 之间有raft选举机制
- 集群
    - cluster meet
    - 16384 槽
    - 本节点找不到槽 返回 MOVE
    - 重新分槽 返回 ASK
    - raft
    
    

