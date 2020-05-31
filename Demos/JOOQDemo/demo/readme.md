1. 先用maven运行jooq generate的插件， 生成code
2. 如果用的是spring embedded的 h2 数据库， 默认的url不知道是什么， 还是要在 application.properties 里面确定一下比较好, 不然遇到table not found 都是一脸蒙比
