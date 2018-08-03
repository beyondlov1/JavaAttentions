#### init

1. 添加依赖

   ```<!-- elasticsearch -->
   <!-- elasticsearch -->
   		 <dependency>
               <groupId> org.springframework.boot </groupId>
               <artifactId> spring-boot-starter-data-elasticsearch </artifactId>
   </dependency>
   ```

2. 添加配置

   application.properties中添加配置

   ```
   spring.data.elasticsearch.repositories.enabled = true
   spring.data.elasticsearch.cluster-nodes =127.0.0.1\:9300
   ```

3. 創建实体类

   添加@Document的注解，并且填寫參數

   注意： 要加上 @id 和 @Field 的注解

4. 继承接口 (DAO   类似mybatis)

   - 简单的增删改查

   ```
   public interface BookSearchRepository extends ElasticsearchRepository<Book, Long> {
   	List<Book> findBookByAuthor(String author);
   }
   ```

   只在接口中写方法就可以了， 格式为find…By, read…By, query…By, count…By, and get…By (简单的可以这么干)

   参考： https://www.jianshu.com/p/35f9f867f48f

   

   - 复杂的查询：

   要引入ElasticsearchTemplate这个类

   ```
   @RequestMapping("/testQueryByExample")
   	public List<Book> testQueryByExample(Book book) {
   		MatchPhraseQueryBuilder mpq1 = QueryBuilders.matchPhraseQuery("author", "abc");
   		MatchPhraseQueryBuilder mpq2 = QueryBuilders.matchPhraseQuery("id", 10);
   		BoolQueryBuilder qb2 = QueryBuilders.boolQuery().must(mpq1).must(mpq2);
   
   		SearchQuery query = new NativeSearchQuery(qb2);
   
   		return elasticsearchTemplate.queryForList(query, Book.class);
   }
   //这个应该写到service层
   ```

   参考： https://blog.csdn.net/tianyaleixiaowu/article/details/77965257

   https://blog.csdn.net/qq_36330643/article/details/79640828

#### 让elasticsearch自动生成id

在实体类中不添加@Id的注解就会由elastic自动生成随机字符串的id

#### spring boot+el注意事项

https://www.cnblogs.com/guozp/p/8686904.html

#### spring-boot-data-elasticsearch中，Field注解中文分词无效的解决方案

（妥协的方案）https://www.imooc.com/article/27017

（妥协的方案2，推荐）在app启动，或者在增加条目之前，添加判断，如果index不存在，则自己手动创建index，这样的化注解就会有作用， 但是字段的type是必须要制定的

https://stackoverflow.com/questions/29496081/spring-data-elasticsearchs-field-annotation-not-working

https://github.com/SpringDataElasticsearchDevs/spring-data-elasticsearch-sample-application/commit/d8162db1096805fb56c6f495c7c76b7c85fe894b

#### Field 的 type=keyword 相当于 not_analyzed



#### Repository 查詢

| Keyword               | Sample                                     | Elasticsearch Query String                                   |
| --------------------- | ------------------------------------------ | ------------------------------------------------------------ |
| `And`                 | `findByNameAndPrice`                       | `{"bool" : {"must" : [ {"field" : {"name" : "?"}}, {"field" : {"price" : "?"}} ]}}` |
| `Or`                  | `findByNameOrPrice`                        | `{"bool" : {"should" : [ {"field" : {"name" : "?"}}, {"field" : {"price" : "?"}} ]}}` |
| `Is`                  | `findByName`                               | `{"bool" : {"must" : {"field" : {"name" : "?"}}}}`           |
| `Not`                 | `findByNameNot`                            | `{"bool" : {"must_not" : {"field" : {"name" : "?"}}}}`       |
| `Between`             | `findByPriceBetween`                       | `{"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}` |
| `LessThanEqual`       | `findByPriceLessThan`                      | `{"bool" : {"must" : {"range" : {"price" : {"from" : null,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}` |
| `GreaterThanEqual`    | `findByPriceGreaterThan`                   | `{"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : null,"include_lower" : true,"include_upper" : true}}}}}` |
| `Before`              | `findByPriceBefore`                        | `{"bool" : {"must" : {"range" : {"price" : {"from" : null,"to" : ?,"include_lower" : true,"include_upper" : true}}}}}` |
| `After`               | `findByPriceAfter`                         | `{"bool" : {"must" : {"range" : {"price" : {"from" : ?,"to" : null,"include_lower" : true,"include_upper" : true}}}}}` |
| `Like`                | `findByNameLike`                           | `{"bool" : {"must" : {"field" : {"name" : {"query" : "?*","analyze_wildcard" : true}}}}}` |
| `StartingWith`        | `findByNameStartingWith`                   | `{"bool" : {"must" : {"field" : {"name" : {"query" : "?*","analyze_wildcard" : true}}}}}` |
| `EndingWith`          | `findByNameEndingWith`                     | `{"bool" : {"must" : {"field" : {"name" : {"query" : "*?","analyze_wildcard" : true}}}}}` |
| `Contains/Containing` | `findByNameContaining`                     | `{"bool" : {"must" : {"field" : {"name" : {"query" : "**?**","analyze_wildcard" : true}}}}}` |
| `In`                  | `findByNameIn(Collection<String>names)`    | `{"bool" : {"must" : {"bool" : {"should" : [ {"field" : {"name" : "?"}}, {"field" : {"name" : "?"}} ]}}}}` |
| `NotIn`               | `findByNameNotIn(Collection<String>names)` | `{"bool" : {"must_not" : {"bool" : {"should" : {"field" : {"name" : "?"}}}}}}` |
| `Near`                | `findByStoreNear`                          | `Not Supported Yet !`                                        |
| `True`                | `findByAvailableTrue`                      | `{"bool" : {"must" : {"field" : {"available" : true}}}}`     |
| `False`               | `findByAvailableFalse`                     | `{"bool" : {"must" : {"field" : {"available" : false}}}}`    |
| `OrderBy`             | `findByAvailableTrueOrderByNameDesc`       | `{"sort" : [{ "name" : {"order" : "desc"} }],"bool" : {"must" : {"field" : {"available" : true}}}}` |

#### queryForList獲取所有查詢結果

```
SearchQuery query = new NativeSearchQueryBuilder().withFilter(termQuery)
			.withPageable(PageRequest.of(0, (int) customerRepository.count()))// 返回所有结果
			.build();
```



repository继承QuerydslPredicateExecutor时package报错

```
interface UserRepository extends CrudRepository<User, Long>, QuerydslPredicateExecutor<User> {
}
```

解决办法：导包

```
<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-core</artifactId>
		</dependency>
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-apt</artifactId>
		</dependency>
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-jpa</artifactId>
		</dependency>
```

####  QuerydslPredicateExecutor (未解决)

```
package com.beyond.entity;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;

public class QCustomer extends EntityPathBase<Customer> {

	public QCustomer(String type) {
		super(Customer.class, type);
		// TODO Auto-generated constructor stub
	}

	public final StringPath name = createString("name");

	public final StringPath description = createString("description");

}

```



```
public List<Customer> queryByNameLikeOrderByAge3(Customer customer) {
		QCustomer doc = new QCustomer("doc");
		 Predicate predicate = doc.name.startsWith("s");
		 Iterable<Customer> findAll = customerRepository.findAll(predicate);
		 for (Customer cus : findAll) {
		 System.out.println(cus.getName());
		 }
		return null;
	}

```

报错： QueryDsl Support has not been implemented yet.

据说还没做好：https://groups.google.com/forum/#!topic/spring-data-elasticsearch-devs/Xk8l4tk4rKo

如果做好可以参考http://www.querydsl.com/static/querydsl/latest/reference/html/ch02s05.html

https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#repositories