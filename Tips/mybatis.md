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

