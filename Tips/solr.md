### 创建core
solr create -c <name>

### 上传文档
windows:
java -Dauto=yes -Dc=core_test2 -jar example\exampledocs\post.jar example\exampledocs\*.xml
参考： https://www.cnblogs.com/shaosks/p/7390523.html

### field/fieldType ... 
参考：https://blog.csdn.net/supermao1013/article/details/83628344

### df
schema中可以设置默认的查询字段, 这会导致指定了qf却查出更多documnet
所以, 如果qf失效了, 可以先看一下是不是设置了df(solrconfig.xml)
参考: https://lucene.apache.org/solr/guide/6_6/the-standard-query-parser.html#TheStandardQueryParser-StandardQueryParserParameters

### 与其他字段进行比较的筛选条件



q=+"湖北"&fq={!frange l=0}sub(stock_available,mul(drug_id,0.5))&fq=provider_id:781&fq=wholesale_type:9&qf=drugname_boolean^2&qf=drugname_pinyin_text&qf=druginfo_cn_name_boolean^4&qf=druginfo_common_name_boolean^4&qf=drugname_text&qf=druginfo_cn_name_text&qf=approval&sort=is_recommend desc,order asc&start=0&rows=10

https://blog.csdn.net/jiangchao858/article/details/53844584

http://lucene.apache.org/solr/guide/7_2/function-queries.html#available-functions

http://lucene.apache.org/solr/guide/7_2/other-parsers.html#function-query-parser





### dataimport需要加入依赖

  <lib dir="${solr.install.dir:../../../..}/dist/" regex="solr-dataimporthandler-.*\.jar" />

和 mysql driver依赖

https://www.jianshu.com/p/4e9eecd3dce4


可配置多个数据源

两个entity可以进行关联, 关联方法为嵌套: 在导入solr时, 会把子entity的属性和父entity的属性进行合并, 重复的属性名称会覆盖.

增量更新时, 只有父entity改变了才会更新这一条数据.

修改时区--在增量更新时需要判断时间戳, 但是默认是使用UTC 时间, 有时区问题
需要将 solr.in.sh 或者 solr.in.cmd 中设置  set SOLR_TIMEZONE=UTC+8  (前边的REM要去掉)
具体参考: https://blog.csdn.net/weixin_42170236/article/details/102834886

dataimport 取消:

http://192.168.0.42:8983/solr/erp_order/dataimport?command=status

### reRank

可以对reRankQuery选出的进行分数重新计算, 可以实现类似的提升效果

q=+"999"&qf=drugname_text&fl=id,score,drugname_text&rq={!rerank reRankQuery=drugname_text:感冒 reRankDocs=1000 reRankWeight=3}