#### jackrabbit
jackrabbit是jcr（content repository for java）标准的实现
能够通过jcr的标准接口对repository进行操作
服务端：用的Node（类似于xml的增删改查）
客户端：依赖webDav的协议，对服务端进行增删改查操作
#### webDav
webDav是对http协议的扩展，增加了put，delete，mkCol等方法
客户端实现webDav主要是以来HttpClient发送请求和HttpPut/HttpMkCol等webDav方法决定发送什么请求
#### httpClient
见TestWebDavClient Demo
