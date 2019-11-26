### head 安装
https://blog.csdn.net/sqlaowen/article/details/81735441 
一开始连不上是因为跨域问题
要在elasticsearch.yml中添加
http.cors.enabled: true
http.cors.allow-origin: "*"
