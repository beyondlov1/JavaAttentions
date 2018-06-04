# hibernate

## hibernate的目的就是让人感觉不到用数据库

## 配置文件

- 四大属性
- hibernate.dialect 方言
- hibernate.connection.provider_class > dataSource
- hbm2ddl.auto > update
- show_sql format_sql
- ibernate.current_session_context_class > thread绑定到当前线程
- hibernate.cache.use_second_level_cache hibernate.cache.region.factory_class 二级缓存
- mapping 加载mapping文件
- class-cache设置那些类用缓存

## mapping文件

实体类与表之间的映射关系

class (name) (table)

id (name) (column) > generator
property (name) (column)

set/list/map/array (name) [其他配置] > key(column) one-to-many (class)
一对多是在多的一方加一列，配置的是加的那一列

 
many-to-one (name) (class) (column) [其他配置]
多对一在自己表里配置对应一方的那一列

## 多对一，一对多/多对多 的其他配置

**cascade**: 
	
	save-update/delete/all
一个做什么另一个跟着做什么，类比：删除板块就要删除板块下所有帖子
一般配置在一方,例如：

	Customer-Order， 配置在Customer方 all，如果保存Customer，则自动保存里面的Order。  
	如果删除Customer，Order也都被删除。

**fetch lazy**
用于集合关联
一方配置：
fetch: select/subselect/join 决定如何查询
lazy: true/false/extra 决定何时查询

多方配置：
fetch: select/join
lazy: false/proxy(由一方决定)


## 获取session

- SessionFactory factory = new Configuration().configure();
- factory.openSession/factory.getCurrentSession/或者自己绑定线程ThreadLocal

## CRUD

- save/saveOrUpdate
- delete
- update

数据加载/查询
1. get/load 区别：get方法调用直接执行查询语句，load方法在用的时候才会发出查询语句（只会生成一个代理对象，只存着id）
2. hql  
	
	    ：name  
    	"from java.lang.Object" 

    	分页：
    	query.setFirstResult
    	query.setMaxResult

		内链接：
		select c from Customer c inner join c.orders;//直接查到所有有订单的顾客，但是order并没有查询出来
		迫切内链接：
		select c from Customer c inner join fetch c.orders; //直接加载出所有数据
3. sql
4. QBC
5. 级联加载（在配置了关联关系的前提下）
6. 命名查询 在mapping文件加入query节点
