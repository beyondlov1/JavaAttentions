# elasticsearch

#### 轻量搜索

```
/megacorp/employee/_search?q=last_name:Smith
```

#### 查询表达式

```
GET /megacorp/employee/_search
{
    "query" : {
        "match" : {
            "last_name" : "Smith"
        }
    }
}
```

query下可以有 match，bool, match_phrase, fuzzy， term

bool下可以有 must ，should， must_not, filter

#### 全文搜索

```
GET /megacorp/employee/_search
{
    "query" : {
        "match" : {
            "about" : "rock climbing" 
        }
    }
}
```

#### 短语搜索

```
GET /megacorp/employee/_search
{
    "query" : {
        "match_phrase" : {
            "about" : "rock climbing"
        }
    }
}
```

#### range查询

```
{
    "range": {
        "age": {
            "gte":  20,
            "lt":   30
        }
    }
}
```

#### exsits查询

```
{
    "exists":   {
        "field":    "title"
    }
}
```

#### 高亮搜索

```
GET /megacorp/employee/_search
{
    "query" : {
        "match_phrase" : {
            "about" : "rock climbing"
        }
    },
    "highlight": {
        "fields" : {
            "about" : {}
        }
    }
}
```

#### 嵌套查询

由于嵌套对象 被索引在独立隐藏的文档中，我们无法直接查询它们。 相应地，我们必须使用 [`nested` 查询](https://www.elastic.co/guide/en/elasticsearch/reference/5.6/query-dsl-nested-query.html)去获取它们： 

```
GET /my_index/blogpost/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "title": "eggs" 
          }
        },
        {
          "nested": {
            "path": "comments", 
            "query": {
              "bool": {
                "must": [ 
                  {
                    "match": {
                      "comments.name": "john"
                    }
                  },
                  {
                    "match": {
                      "comments.age": 28
                    }
                  }
                ]
              }
            }
          }
        }
      ]
}}}
```

#### 日期

存入时格式设置: 

```json
PUT my_index
{
  "mappings": {
    "my_type": {
      "properties": {
        "date": {
          "type":   "date",
          "format": "yyyy-MM-dd"
        }
      }
    }
  }
}
```



##### CUD

**取回多个文档**

```
GET /_mget
{
   "docs" : [
      {
         "_index" : "website",
         "_type" :  "blog",
         "_id" :    2
      },
      {
         "_index" : "website",
         "_type" :  "pageviews",
         "_id" :    1,
         "_source": "views"
      }
   ]
}
```

**批量操作**

```
POST /_bulk
{ "delete": { "_index": "website", "_type": "blog", "_id": "123" }} 
{ "create": { "_index": "website", "_type": "blog", "_id": "123" }}
{ "title":    "My first blog post" }
{ "index":  { "_index": "website", "_type": "blog" }}
{ "title":    "My second blog post" }
{ "update": { "_index": "website", "_type": "blog", "_id": "123", "_retry_on_conflict" : 3} }
{ "doc" : {"title" : "My updated blog post"} } 
```

**多索引，多类型查询**

`/_search`

在所有的索引中搜索所有的类型

`/gb/_search`

在 `gb` 索引中搜索所有的类型

`/gb,us/_search`

在 `gb` 和 `us` 索引中搜索所有的文档

`/g*,u*/_search`

在任何以 `g` 或者 `u` 开头的索引中搜索所有的类型

`/gb/user/_search`

在 `gb` 索引中搜索 `user` 类型

`/gb,us/user,tweet/_search`

在 `gb` 和 `us` 索引中搜索 `user` 和 `tweet` 类型

`/_all/user,tweet/_search`

在所有的索引中搜索 `user` 和 `tweet` 类型



**分页**

```
GET /_search?size=5
GET /_search?size=5&from=5
GET /_search?size=5&from=10
```



#### 精确值 全文

*精确值* 如它们听起来那样精确。例如日期或者用户 ID，但字符串也可以表示精确值，例如用户名或邮箱地址。对于精确值来讲，`Foo` 和 `foo` 是不同的，`2014` 和 `2014-09-15` 也是不同的。

另一方面，*全文* 是指文本数据（通常以人类容易识别的语言书写），例如一个推文的内容或一封邮件的内容。

#### 倒排索引

为了创建倒排索引，我们首先将每个文档的 `content` 域拆分成单独的词（ `词条` 或 `tokens`）

创建一个包含所有**不重复**词条的排序列表，然后列出每个词条出现在哪个文档。 



#### 分析器

*分析* 包含下面的过程：

- 首先，倒排索引
- 之后，词条统一化

分析器执行上面的工作。 *分析器* 实际上是将三个功能封装到了一个包里：

- 字符过滤器（处理符号）

  首先，字符串按顺序通过每个 *字符过滤器* 。他们的任务是在分词前整理字符串。一个字符过滤器可以用来去掉HTML，或者将 `&` 转化成 `and`。

- 分词器（拆分出词条）

  其次，字符串被 *分词器* 分为单个的词条。一个简单的分词器遇到空格和标点的时候，可能会将文本拆分成词条。

- Token 过滤器（大小写，同义词）

  最后，词条按顺序通过每个 *token 过滤器* 。这个过程可能会改变词条（例如，小写化 `Quick` ），删除词条（例如， 像 `a`， `and`， `the` 等无用词），或者增加词条（例如，像 `jump` 和 `leap` 这种同义词）。

Elasticsearch提供了开箱即用的字符过滤器、分词器和token 过滤器。 这些可以组合起来形成自定义的分析器以用于不同的目的。我们会在 [自定义分析器](https://www.elastic.co/guide/cn/elasticsearch/guide/cn/custom-analyzers.html) 章节详细讨论。

**分析器执行时机**

- 当你查询一个 *全文* 域时， 会对查询字符串应用相同的分析器，以产生正确的搜索词条列表。
- 当你查询一个 *精确值* 域时，不会分析查询字符串， 而是搜索你指定的精确值。（时间，数字，not_analysed）

#### 指定映射

```
PUT /gb 
{
  "mappings": {
    "tweet" : {
      "properties" : {
        "tweet" : {
          "type" :    "string",
          "analyzer": "english"
        },
        "date" : {
          "type" :   "date"
        },
        "name" : {
          "type" :   "string"
        },
        "user_id" : {
          "type" :   "long"
        }
      }
    }
  }
}
```

#### 排序

```
GET /_search
{
    "query" : {
        "bool" : {
            "filter" : { "term" : { "user_id" : 1 }}
        }
    },
    "sort": { "date": { "order": "desc" }}
}
```

```
GET /_search
{
    "query" : {
        "bool" : {
            "must":   { "match": { "tweet": "manage text search" }},
            "filter" : { "term" : { "user_id" : 2 }}
        }
    },
    "sort": [
        { "date":   { "order": "desc" }},
        { "_score": { "order": "desc" }}
    ]
}
```

**多值字段排序**

一个字段有多个值

```
"sort": {
    "dates": {
        "order": "asc",
        "mode":  "min"
    }
}
```

#### 分布式检索

https://www.elastic.co/guide/cn/elasticsearch/guide/cn/_query_phase.html

#### 索引配置

```
PUT /my_temp_index
{
    "settings": {
        "number_of_shards" :   1,
        "number_of_replicas" : 0,
        "analysis": {
            "analyzer": {
                "es_std": {
                    "type":      "standard",
                    "stopwords": "_spanish_"
                }
            }
        }
    }
}
```

#### 自定义分析器

```
PUT /my_index
{
    "settings": {
        "analysis": {
            "char_filter": { ... custom character filters ... },
            "tokenizer":   { ...    custom tokenizers     ... },
            "filter":      { ...   custom token filters   ... },
            "analyzer":    { ...    custom analyzers      ... }
        }
    }
}
```

https://www.elastic.co/guide/cn/elasticsearch/guide/cn/custom-analyzers.html

```
PUT /my_index
{
    "settings": {
        "analysis": {
            "char_filter": {
                "&_to_and": {
                    "type":       "mapping",
                    "mappings": [ "&=> and "]
            }},
            "filter": {
                "my_stopwords": {
                    "type":       "stop",
                    "stopwords": [ "the", "a" ]
            }},
            "analyzer": {
                "my_analyzer": {
                    "type":         "custom",
                    "char_filter":  [ "html_strip", "&_to_and" ],
                    "tokenizer":    "standard",
                    "filter":       [ "lowercase", "my_stopwords" ]
            }}
}}}
```

#### 动态映射

当新添加新字段时如何处理：

用 `dynamic` 配置来控制这种行为 ，可接受的选项如下：

- `true`

  动态添加新的字段--缺省

- `false`

  忽略新的字段

- `strict`

  如果遇到新字段抛出异常

```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "dynamic":      "strict", 
            "properties": {
                "title":  { "type": "string"},
                "stash":  {
                    "type":     "object",
                    "dynamic":  true 
                }
            }
        }
    }
}
```



当 Elasticsearch 遇到一个新的字符串字段时，它会检测这个字段是否包含一个可识别的日期 ，自动识别为日期

如果想关闭，一直作为string，如下设置：

```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "date_detection": false
        }
    }
}
```



使用 `dynamic_templates` ，你可以完全控制 新检测生成字段的映射。 

```
PUT /my_index
{
    "mappings": {
        "my_type": {
            "dynamic_templates": [
                { "es": {
                      "match":              "*_es", 
                      "match_mapping_type": "string",
                      "mapping": {
                          "type":           "string",
                          "analyzer":       "spanish"
                      }
                }},
                { "en": {
                      "match":              "*", 
                      "match_mapping_type": "string",
                      "mapping": {
                          "type":           "string",
                          "analyzer":       "english"
                      }
                }}
            ]
}}}
```

https://www.elastic.co/guide/cn/elasticsearch/guide/cn/custom-dynamic-mapping.html

#### 结构化搜索

日期、时间和数字都是结构化的：它们有精确的格式 

#### 查询多个精确值

```
GET /my_store/products/_search
{
    "query" : {
        "constant_score" : {
            "filter" : {
                "terms" : { 
                    "price" : [20, 30]
                }
            }
        }
    }
} 
```

`term` 和 `terms` 是 *必须包含（must contain）* 操作 

如果要实现相等，要增加一个字段，存储term数目

#### 缓存

https://www.elastic.co/guide/cn/elasticsearch/guide/cn/filter-caching.html

#### 必须有n个匹配 

minimum_should_match

```
{
    "match": {
        "title": {
            "query":                "quick brown fox",
            "minimum_should_match": "75%"
        }
    }
}
```

#### 增加权重

should可以增加权重

如果should中有多个查询，而这些查询需要区分权重，可以用boost：

```
GET /_search
{
    "query": {
        "bool": {
            "must": {
                "match": {  
                    "content": {
                        "query":    "full text search",
                        "operator": "and"
                    }
                }
            },
            "should": [
                { "match": {
                    "content": {
                        "query": "Elasticsearch",
                        "boost": 3 
                    }
                }},
                { "match": {
                    "content": {
                        "query": "Lucene",
                        "boost": 2 
                    }
                }}
            ]
        }
    }
}
```

#### 最佳匹配查询

一般我们都用bool查询，而bool查询的评分规则为

1. 它会执行 `should` 语句中的两个查询。
2. 加和两个查询的评分。
3. 乘以匹配语句的总数。
4. 除以所有语句总数（这里为：2）。

其经过了许多计算， 不一定能够找到最佳的匹配结果

dis_max 则是只将最佳匹配的评分作为最后的评分结果，所以这种方法更能保证结果是最佳匹配的

**tie_breaker**

`dis_max` 查询只会简单地使用 *单个* 最佳匹配语句的评分， 如果两个文档都有匹配的词，并且匹配个数相同，则会导致评分相同，所以tie_breaker则是考虑到了其他的文档，相当于dis_max和bool的折中

#### multi_match 

使用场景： match方式相同的多个字段

可以模糊匹配

```
{
    "multi_match": {
        "query":  "Quick brown fox",
        "fields": "*_title"
    }
}
```

#### 聚合

桶 + 指标

