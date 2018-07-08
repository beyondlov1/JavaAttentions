#### 新建线程的方法
1. Thread
2. Runnable
3. Timer TimerTask
#### 开始线程
thread.start
#### 让出CPU
sleep yeild join
#### 后台线程
setDaemon()
new Timer(true)
#### 线程同步
sychronized
#### 停止线程
stop 一般改while的条件
#### 线程通讯
notifyAll notify wait 和锁有关（内部锁）
#### 中断
interrupt 会跳到异常处理代码块
#### 线程组
有用的方法：unCaughtExceptionHandler
#### ThreadLocal
#### concurrent并发包
Lock
Condition (通讯)
Callable Future FutureTask
Executor Executors
BlockingQueue ArrayBlockingQueue
