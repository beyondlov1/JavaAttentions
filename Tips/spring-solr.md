### 整合spring-data-solr
如果要用 solrTemplate 要配置一个 solrClient：HttpSolrClient.Builder
要在配置文件配置 host, solrClient 中也要配置相应的url, 否则spring 找不到

查询可用SimpleQuery, 条件用 Criteria , 
simpleQuery(Criteria.Wildcard)
Criteria(field)


### solr elevate
solr的 elevate 的提升受filterQuery的影响，而不受query的影响（即结果集中不会出现filter掉的结果， 即使elevateIds中声明了）
elevate 在xml配置的时候会配置一个query的字符串， 以判断搜索哪个词的时候会出现

参考官方文档：
The fq Parameter
Query elevation respects the standard filter query (fq) parameter. That is, if the query contains the fq parameter, all results will be within that filter even if elevate.xml adds other documents to the result set.
https://lucene.apache.org/solr/guide/6_6/the-query-elevation-component.html

### converter
与solr相关的converter有两种： 一个是MappingSolrConverter,一个是SolrJConverter
MappingSolrConverter是用来每个字段转换的，里面加的converter可以是字段的类型
SolrJConverter可以SolrDocument和Entity进行转化（完全自己掌握)
Converter的话就实现Converter的接口，添加到CustomConversion中。
```
@Bean
    public SolrTemplate solrTemplate(SolrClient solrClient){
        MappingSolrConverter solrConverter = new MappingSolrConverter(new SimpleSolrMappingContext());
        SolrTemplate solrTemplate = new SolrTemplate(solrClient);
        solrConverter.getConversionService().addConverter(new StringToBigDecimalConverter());
        solrTemplate.setSolrConverter(solrConverter);
        return solrTemplate;
    }
```
见 solr-spring-demo-2
