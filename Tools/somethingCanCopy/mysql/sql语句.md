### sqlServer 从一个表批量插入到另一个表
insert into JDBC.dbo.test (id,name) (select 	ROW_NUMBER() OVER(Order by C_ID desc ) +10 AS RowId, C_NAME from JDBC..TEST01)  

参考: https://zhidao.baidu.com/question/111313189.html
