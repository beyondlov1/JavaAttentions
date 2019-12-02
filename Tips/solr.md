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