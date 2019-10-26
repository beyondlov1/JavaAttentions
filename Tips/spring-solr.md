### 整合spring-data-solr
如果要用 solrTemplate 要配置一个 solrClient：HttpSolrClient.Builder
要在配置文件配置 host, solrClient 中也要配置相应的url, 否则spring 找不到

查询可用SimpleQuery, 条件用 Criteria , 
simpleQuery(Criteria.Wildcard)
Criteria(field)