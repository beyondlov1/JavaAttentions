### 不能加载 db.properties 文件

- 版本: spring: 5.0.7 mybatis: 3.4.6
- 报错: can not resolve [${jdbc.driver}].....
- 原因: 在spring里使用org.mybatis.spring.mapper.MapperScannerConfigurer  进行自动扫描的时候，设置了sqlSessionFactory  的话，可能会导致PropertyPlaceholderConfigurer失效，也就是用${jdbc.username}这样之类的表达式，将无法 获取到properties文件里的内容。  导致这一原因是因为，**MapperScannerConigurer实际是在解析加载bean定义阶段的，这个时候要是设置  sqlSessionFactory的话，会导致提前初始化一些类**，这个时候，PropertyPlaceholderConfigurer还没来得及替 换定义中的变量，导致把表达式当作字符串复制了。 但如果不设置sqlSessionFactory  属性的话，就必须要保证sessionFactory在spring中名称一定要是sqlSessionFactory  ，否则就无法自动注入。又或者直接定义 MapperFactoryBean ，再或者放弃自动代理接口方式。  
- 解决办法: **把spring中扫描mybatis的mapper的配置写到另外一个文件中，然后把新文件的beans的default-autowire="byName"属性去掉**

#### spring mvc 解析 json 出错

**错误:** 

```java
org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory$EnhancedResultObjectProxyImpl]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory$EnhancedResultObjectProxyImpl and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) (through reference chain: java.util.ArrayList[0]->com.beyond.entity.Book_$$_jvstcf3_0["handler"])
```

**原因:** spring mvc 将list 解析成 json 时出错, 可能与mybatis 懒加载有关

**解决:** 

在所有相关的类前加上@JsonIgnoreProperties, 作用是json序列化时忽略bean中的一些属性序列化和反序列化时抛出的异常.

```
@JsonIgnoreProperties(value = {"handler"})
public abstract class BaseEntity implements Serializable
```

参考: https://blog.csdn.net/wgp15732622312/article/details/79951977