### thrift

定义了与语言无关的传输格式, 类似JSON, 只不过使用二进制字节码来表示

主要用来 RPC

### 安装

1. 下载编译器

2. 编写 .thrift 文件, (注意: namespace 要加 java 的字样, 如 namespace java com.beyond.test)

3. ```
   thrift -r --gen java tutorial.thrift
   ```

4. 会生成相应java文件

### 客户端/服务端

thrift 将远程调用抽象为了六层

1. server/client
2. Thrift Protocol
3. Transport Wrapper
4. Low-Level Transport
5. Language
6. Operating System

demo: thriftDemos/thrift-demo

使用的时候可以从各个层进行自由组合

参考: https://github.com/apache/thrift