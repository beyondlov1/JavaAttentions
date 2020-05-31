### 占位符 连接符

#{}为占位符, ${}为连接符

区别: 占位符在生成sql语句时为?, 连接符为内部的内容直接写到里面

### 自动生成uuid主键

```xml
<insert id="insertUser" parameterType="com.beyond.entity.User">
	<selectKey keyProperty="id" resultType="string" order="BEFORE">
	select replace(uuid(),'-','') from dual
	</selectKey>
		insert into User (id, username,password) values(#{id},#{username},#{password})
</insert>
```

sql 语句中的 **id **不能省

java 代码中必须要有 commit() 

### 同时执行插入多条数据

1. 在 JDBC的url后加入: allowMultiQueries=true
2. 

```xml
<insert id="addOwner" parameterType="owner">
	<selectKey keyProperty="id" resultType="string" order="BEFORE" keyColumn="id">
		select replace(uuid(),'-','')
	</selectKey>
	insert into owner(id,username,password) values(#{id},#{username},#{password});
    
	insert into book (id,bookName,price,ownerId) values 
	<if test="books.size()>0">
		<foreach collection="books" separator="," index="index" item="book">
			((select replace(uuid(),'-','')),#{book.bookName},#{book.price},#{id})
		</foreach>
	</if>
</insert>
```

关键:   ...((select replace(uuid(),'-',''))....

这样每执行一次insert 就会生成一个uuid

#### select选择多条数据

mybatis 根据**接口返回值的类型**来决定用什么方法

 如果返回值为 User 则会用selectOne 的方法, 如果返回值为list ,则返回list集合

#### 重名导致结果赋值混乱问题

- 问题: 多表联查可能会导致列名相同, 从而在赋值时会将非空的值赋值到重名的列上
- 解决办法: select 语句中重命名 (暂时没发现更好的解决办法, 以后可以研究一下hibernate是怎么做的)

#### debug显示查询语句

```
  <settings>
        <!-- 打印查询语句 -->
        <setting name="logImpl" value="STDOUT_LOGGING" />
    </settings>
```

#### foreach
<select id="dynamicForeachTest" parameterType="java.util.List" resultMap="Users">  
    select id，name from t_blog where id in  
    <foreach collection="list" index="index" item="item" open="(" separator="," close=")">  
        #{item.id}  
    </foreach>  
</select>  

### mybatis绑定错误 org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.yo.news.user.mapper.UserMapper.getUserByTelPwd
pom.xml
<build>
<resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
</resources>

https://www.jianshu.com/p/800fe918cc7a

### typeHandler

insert 要在 #{} 里面配置 typeHandler=xxx 才能生效

全局注册 typeHandler 要在 configuration 文件中 <typeHandlers> 标签中声明,  如果构造方法中有 Class 的参数, 需要写上 JavaType , 否则报错

### plugin

```
package com.beyond.demo;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

@Intercepts(
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
)
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        RowBounds rowBounds = (RowBounds) args[2];
        String limit = String.format(" LIMIT %s %s", rowBounds.getOffset(), rowBounds.getLimit());
        sql = sql+limit;
        Class<? extends BoundSql> boundSqlClass = boundSql.getClass();
        Field sqlField = boundSqlClass.getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql,sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
```

config.xml 里注册

demo: Demos/mybatisDemo

### 如果总是提示spring中没有mapper
看有没有加 Mapper 的注解
如果加了注解， idea 还能正常显示
那么可能是依赖有问题， mybatis-spring-boot-start 是第三方的 ， 要写版本号！要写版本号！要写版本号！

