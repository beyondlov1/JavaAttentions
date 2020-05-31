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

#### jackrabbitexplorer

用来查看jackrabbit的repository

是一个webapp的war包

地址: http://localhost:8080/jackrabbitexplorer/

参考: http://priocept.com/2011/09/16/announcing-jackrabbit-explorer-admin-tool-for-jcr-repositories/

https://code.google.com/archive/p/jackrabbitexplorer/wikis/GettingStarted.wiki

#### JCR explorer

Explore Jackrabbit Repository, Search, Query and Manage your JCR Repo Visually 

项目地址: https://github.com/mehmoodz/jcr-explorer

#### java使用HttpGet下载文件

```
public static byte[] get(String url, Map<String, String> headers) throws ClientProtocolException, IOException {
        logger.info("Ready Get Request Url[{}]", url);
        HttpGet get = new HttpGet(url);
        setHttpHeaders(get, headers);
        HttpResponse response = HttpClients.createDefault().execute(get);
        if (null == response || response.getStatusLine() == null) {
            logger.info("Post Request For Url[{}] is not ok. Response is null", url);
            return null;
        } else if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            logger.info("Post Request For Url[{}] is not ok. Response Status Code is {}", url,
                    response.getStatusLine().getStatusCode());
            return null;
        }
        return EntityUtils.toByteArray(response.getEntity());
    }
```

https://blog.csdn.net/User_xiangpeng/article/details/73742227


### jackrabbit ubuntu 503
原因是路径中存在汉字
