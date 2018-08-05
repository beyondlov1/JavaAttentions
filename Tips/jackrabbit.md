#### jackrabbit
jackrabbit是jcr（content repository for java）标准的实现
能够通过jcr的标准接口对repository进行操作
服务端：用的Node（类似于xml的增删改查）
客户端：依赖webDav的协议，对服务端进行增删改查操作

#### webDav
webDav是对http协议的扩展，增加了put，delete，mkCol等方法
客户端实现webDav主要是以来HttpClient发送请求和HttpPut/HttpMkCol等webDav方法决定发送什么请求
国内只有坚果云支持webDav（貌似）
参考：
https://www.ibm.com/developerworks/cn/java/j-lo-jackrabbit/index.html （IBM的介绍如何搭建webDav客户端和如何开启服务端的webdav功能（Apache的http服务器支持，但是默认没开））

#### httpClient
见WebDavClientDemo
用法参考：
https://hc.apache.org/httpcomponents-client-ga/quickstart.html（官方）
http://www.baeldung.com/httpclient-4-basic-authentication
http://yiyu.iteye.com/blog/896302（这个有点老，方法都不对，但是思想是一样的）


http://jetway.iteye.com/blog/49038（这个乱七八糟啥都有）

