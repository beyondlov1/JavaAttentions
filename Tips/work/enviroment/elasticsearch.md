#### install 6.3.2

1. 官網下載（本次下載的msi）

2. 安裝（選擇創建服務）

3. 打開 http://localhost:9200  是否返回一個json

4. 如果要讓其他ip訪問到要更改config/elasticsearch.yml：

   ```
   # 修改一下ES的监听地址，这样别的机器也可以访问
   network.host: 0.0.0.0
   
   # 增加新的参数，这样head插件可以访问es
   http.cors.enabled: true
   http.cors.allow-origin: "*"
   ```

5. 訪問 http://172.16.xxx.xxx:9200/ 如果還是訪問不到， 更改防火墻， 開放9200端口

   windows： 防火墻-入站規則-特定端口balabala

安裝head：

1. 安装node.js 

2. 安装grunt 

   路径切到nodejs目錄下.

   ```
   npm install -g grunt-cli
   ```

3. 找個文件夾 git clone git://github.com/mobz/elasticsearch-head.git 

4. 修改head源码

   由于直接执行有很多限制，比如无法跨机器访问。因此需要用户修改两个地方：

   目录：D:\Module\elasticsearch-head\Gruntfile.js：

   ```
   connect: {
       server: {
           options: {
               port: 9100,
               hostname: '*',
               base: '.',
               keepalive: true
           }
       }
   }
   ```

   增加hostname属性，设置为*

   

   修改链接地址

   目录：D:\Module\elasticsearch-head\_site\app.js

   修改head的连接地址:

   ```
   this.base_uri = this.config.base_uri || this.prefs.get("app-base_uri") || "http://localhost:9200";
   ```

   把localhost修改成你es的服务器地址，如：

   ```
   this.base_uri = this.config.base_uri || this.prefs.get("app-base_uri") || "http://ip:9200";
   ```

5. 在head源碼位置： npm install  之後 grunt server

6. 打開 http://localhost:9100/

注意： 如果顯示未連接，可能是防火墻之類沒打開， 或者重啓一下服務就好了



#### ik 中文分词插件

安装：

①下载编译好的安装包：[https://github.com/medcl/elasticsearch-analysis-ik/releases](https://link.jianshu.com?t=https://github.com/medcl/elasticsearch-analysis-ik/releases)。注意下载版本要对应。
 ②下载好了之后解压，将解压后的文件夹放在elasticsearch目录下的plugins目录下，并重命名为analysis-ik
 ③将analysis-ik下config目录整个拷贝到elasticsearch目录下的config目录下，并重命名为ik
 ④ 重启elasticsearch

参考： https://www.jianshu.com/p/8b0c055fd7be



使用：

在创建index的时候制定分词

```
$ curl -X PUT 'localhost:9200/accounts' -d '
{
  "mappings": {
    "person": {
      "properties": {
        "user": {
          "type": "text",
          "analyzer": "ik_max_word",
          "search_analyzer": "ik_max_word"
        },
        "title": {
          "type": "text",
          "analyzer": "ik_max_word",
          "search_analyzer": "ik_max_word"
        },
        "desc": {
          "type": "text",
          "analyzer": "ik_max_word",
          "search_analyzer": "ik_max_word"
        }
      }
    }
  }
}'
```

增：

```
$ curl -X PUT 'localhost:9200/accounts/person/1' -d '
{
  "user": "张三",
  "title": "工程师",
  "desc": "数据库管理"
}' 
```

删：

```
$ curl -X DELETE 'localhost:9200/accounts/person/1'
```

改：

同增

查：

查所有：

```
$ curl 'localhost:9200/accounts/person/_search'
```

查某一个：

```
$ curl 'localhost:9200/accounts/person/_search'  -d '
{
  "query" : { "match" : { "desc" : "软件" }}
}'

！！！！！！要用post！！！！！！
```

参考： http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html （里面单个查询不对）



#### ElasticSearch单机双实例的配置方法

https://knktc.com/2016/06/10/elasticsearch-multiple-instances/



#### elastic自动生成的id的模式

自动生成的 ID 是 URL-safe、 基于 Base64 编码且长度为20个字符的 GUID 字符串。 这些 GUID 字符串由可修改的 FlakeID 模式生成，这种模式允许多个节点并行生成唯一 ID ，且互相之间的冲突概率几乎为零 

#### elastic 轻量级的查询

q= +name:joy +age:>20 +may

