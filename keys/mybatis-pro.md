### 内置数据源
- unpooled
- 创建过程:dataSourceFactory new一个, 然后setProperties, 把username, password什么的设置进去
- 获取连接: initialiizeDriver doGetConnection configureConnection
- pooled: 
- 创建过程: 与unpool 一致
- 获取连接: PoolState PoolConnection
- PoolConnection: realConnection proxyConnection
- PoolState: 定义两个PoolConnection 集合, 闲置集合和激活集合
- 回收连接通过代理connection的close方法截获
- jndi

### 缓存
- Cache: perpetualCache  装饰类: LruCache SynchronizedCache BlockingCache
- perpetualCache: Hashmap
- LruCache 有一个LinkedHashMap 的 keyMap 进行辅助, 定长, 超出后移除最老的, 获取时把对应的keyMap中的位置放到最后
- BlockingCache 加锁粒度时一个key, 里面有个ConcurrentHashmap 保存 reentrantLock, 获取object的时候加锁, 命中了释放, 不命中不释放, 让其他线程等待, 因为要去数据库中取. putObject 的时候释放锁, 让其他线程可以取了. removeObject的时候只释放锁
- cacheKey 会根据各影响因素进行计算, 计算hashcode , checksum, 比较每个项是不是相等
- 一级缓存: perpetualCache , 一般不会出现线程安全问题
- 二级缓存: 线程安全问题和事务问题
- 线程安全问题: sychronizedCache
- 事务的问题: 可能会有脏读的问题, 解决方法 transactionalCache, 在里面有个缓存 entriesToAddOnCommit, 在提交的时候选择提交还是clear. 里面还有个entriesMissedInCache, 主要用来解锁
- 只能支持Read Commit 的级别

### 插件
- 原理: 创建代理对象, 有多少个插件, 就包多少层
- InterceptorChain
- 插件实现Intercepter 接口, 然后用注解标识在哪里进行拦截, 和需要哪些参数