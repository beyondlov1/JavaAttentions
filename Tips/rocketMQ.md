### 安装与启动

1. 添加环境变量:  ROCKETMQ_HOME

2. 启动 mqnamesrv

   ```
   mqnamesrv.cmd -n localhost:9876
   ```

3. 启动 broker

   ```
   mqbroker.cmd -n localhost:9876 autoCreateTopicEnable=true
   ```

   如果不加后边的, 在使用时可能出现 no route .... 

   [[[ 使用时client 要和服务端版本号一样, 不一样不会生效,  坑!!!! ]]]

4. rocketMQ-console

   - <https://github.com/apache/rocketmq-externals/tree/master/rocketmq-console> 

   - clone

   - 修改 application.properties :  rocketmq.config.namesrvAddr=localhost:9876

   - ```
     mvn spring-boot:run
     ```

     访问 localhost:8080

   参考: <https://blog.csdn.net/javahighness/article/details/79449054> 