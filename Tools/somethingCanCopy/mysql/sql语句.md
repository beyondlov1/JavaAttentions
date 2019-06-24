### sqlServer 从一个表批量插入到另一个表
insert into JDBC.dbo.test (id,name) (select 	ROW_NUMBER() OVER(Order by C_ID desc ) +10 AS RowId, C_NAME from JDBC..TEST01)  

参考: https://zhidao.baidu.com/question/111313189.html


### sqlServer 批量更新
update a set a.status=b.status from table1 a,table2 b where   a.id1=b.id1; (未验证)

### SQLite 批量更新
https://codeday.me/bug/20190409/851403.html

### SQLite 批量插入

1> select ... into new_tablename from ... where ... 
2> insert (into) old_tablename select ... from ... where ... 
区别是前者把数据插入一个新表（先建立表，再插入数据），

后者是把数据插入已经存在的一个表中，我个人喜欢后者，因为在编程的结构上，应用的范围上，第二条语句强于前者。

insert into TEST select name,id,age from TEST2;
