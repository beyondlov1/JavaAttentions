# mybatis是面向sql的

#### 就是在配置文件中写一堆sql语句, 在java中调用

#### 主配置文件

- properties
- setting
- typeAliases
- environments 配置数据库参数
- mappers

#### mapper配置

- namespace

- resultMap[id, type]
  assosiation [property, javaType]   collection [property, ofType]

- select [id, parameterType, resultType|resultMap]

  selectKey [keyProperty, keyColumn, resultType, order]

- if foreach | where set

#### 加载配置文件的方式

```java
String resource = "mybatis-config.xml";
InputStream is = Resources.getResourceAsStream(resource);
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
SqlSessionFactory factory = builder.build(is);
SqlSession session = factory.openSession();
BookMapper bookMapper = (BookMapper) session.getMapper(BookMapper.class);
```

spring: 

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd 
		http://www.springframework.org/schema/mvc 
		http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd 
		http://www.springframework.org/schema/context 
		http://www.springframework.org/schema/context/spring-context-3.2.xsd 
		http://www.springframework.org/schema/aop 
		http://www.springframework.org/schema/aop/spring-aop-3.2.xsd 
		http://www.springframework.org/schema/tx 
		http://www.springframework.org/schema/tx/spring-tx-3.2.xsd ">
		
	<!-- 加载外部资源文件 -->
	<bean id="jdbcConfigurer"
	class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	<property name="locations" value="classpath:db.properties" />
</bean>
	
	<!-- 管理数据源 -->
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="${jdbc.driver}"/>
		<property name="url" value="${jdbc.url}"/>
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
	</bean>
	
	<!-- 管理SqlSessionFactory -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 注入数据源 -->
		<property name="dataSource" ref="dataSource"/>
		<!-- 加载mybatis的全局配置文件 -->
		<property name="configLocation" value="classpath:mybatis-config.xml"/>
	</bean>

	
	<!-- 管理单个的代理对象 -->
	<!-- <bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
		<property name="mapperInterface" value="com.beyond.mapper.UserMapper"/>
		<property name="sqlSessionFactory" ref="sqlSessionFactory"/>
	</bean> -->
	
	<!-- 批量代理对象的管理 -->
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="classpath:com.beyond.mapper"/>
	</bean>
	
</beans>
```





#### Mapper方法开发

**mapper就相当于Dao**

要求: 

- **mapper配置文件** 的名要与 **interface** 名相同
- **mapper配置文件** 的名要与 **interface** 在同一个包中
- **mapper配置文件** 的 **namespace** 要写对应接口的全名(包括包名)

##### 关联关系

**要注意重名的问题**

#### 懒加载

1. settings -> lazyLoadingEnabled = true    [fetchType覆盖]

2. aggressiveLazyLoading  如果为true则是任意方法调用都会加载所有, 如果为false则是什么方法调用返回什么的结果, 其他的不会加载

   > aggressive
   >
   > adj. 侵犯的, 侵略的; 有进取精神的; 好斗的, 挑衅的; 有干劲的

3. mapper:

   association:

   ```java
   <association property="owner" javaType="owner" select="com.beyond.mapper.OwnerMapper.queryById" column="ownerId">
   		<id property="id" column="ownerId" />
   		<result property="username" column="username" />
   		<result property="password" column="password" />
   </association>
   ```

   collection:

   ```xml
   <resultMap type="user" id="userBookResultMap">
   		<result property="id" column="user_id" />
   		<result property="username" column="user_username" />
   		<result property="password" column="user_password" />
   		<collection property="ownBooks" ofType="book">
   			<result property="id" column="own_book_id" />
   			<result property="name" column="own_book_name" />
   			<result property="price" column="own_book_price" />
   		</collection>
   		<collection property="borrowBooks" ofType="book">
   			<result property="id" column="borrow_book_id" />
   			<result property="name" column="borrow_book_name" />
   			<result property="price" column="borrow_book_price" />
   		</collection>
   		<collection property="wantToBorrowBooks" ofType="book" select="selectWantBookByUserId" column="user_id" fetchType="lazy"></collection>
   	</resultMap>
   	<select id="selectById" parameterType="string" resultMap="userBookResultMap">
   		select
   		u.id user_id,
   		u.username user_username,
   		u.password user_password,
   		b.id own_book_id,
   		b.name own_book_name,
   		b.price own_book_price,
   		b2.id borrow_book_id,
   		b2.name borrow_book_name,
   		b2.price borrow_book_price
    from user u 
   		left join book b on u.id=b.owner_id
   		left join book b2 on u.id=b2.borrower_id
   	where u.id = #{id}
   	</select>
   	
   	<select id="selectWantBookByUserId" parameterType="string" resultType="book">
   	select * from user u 
   	left join (select * from user_want_borrow_book ub, book b3 where ub.book_id=b3.id) ub2 on u.id=ub2.user_id 
   	where u.id=#{id}
   	</select>
   ```

   ps: collection lazy查询时 select 中的方法返回的 resultType 为集合中 **item的类型** , 查询的方法就是查询一个item的方法

   ps2: 测试时直接输出 **user**对象会把里面的都加载, 而输出**user.getUsername** 则其他的懒加载不会加载,

   select 表示用哪个方法查询,  column 表示用哪一列的数据去查

#### 二级缓存

1. Mapper中加入

   ```xml
   <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
   ```

2. entity 实现 serializable

