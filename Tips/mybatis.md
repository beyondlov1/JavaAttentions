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
