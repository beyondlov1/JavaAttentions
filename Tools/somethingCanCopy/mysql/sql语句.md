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

### sql playground
https://www.db-fiddle.com/

### 查询多个分类的第一条数据
select * from(
		SELECT 
			zf.C_ZFBH AS CZfbh,
			zyxx.C_ZF_ID AS CZfId,
			zf.C_XM AS CXm,
			zf.C_SZJQ AS CSzjq,
			zxx.C_MC  AS CHjzh,
			CASE zyxx.C_IS_ZZ WHEN '1' THEN '组长' ELSE '组员' END AS CHjgw,
			zf.C_ZM AS CZm,
			zf.N_XXQ_NS AS nns,
			zf.N_XXQ_YS AS nys,
			zf.N_XXQ_TS AS nts,
			zf.D_XXQ_ZR AS DXxqZr,
			zf_ext.C_JTZZ_QH AS CJtzzQh,
			zf_ext.C_JTZZ_MX As CjtzzMx,
			CASE zf.C_ZYBS WHEN '1' THEN '在押' ELSE '离监' END AS CZybs,
			ZFBZBD.C_JSH  AS CJsh,
			ZFBZBD.C_CWH  AS CCwh,
			 ROW_NUMBER() OVER(Order by zf.C_ZFBH desc ) AS RowId
		FROM 
			DB_YZGL..T_YZGL_ZYXX zyxx
		LEFT JOIN 
			DB_ZF..T_ZF zf ON zf.C_ID = zyxx.C_ZF_ID 
		LEFT JOIN 
			DB_ZF..T_ZF_EXT zf_ext ON zf_ext.C_ZF_ID = zf.C_ID AND zf_ext.C_SFYX = '1'
		Left JOIN
		    DB_YZGL..T_YZGL_ZXX zxx ON zxx.C_ID = zyxx.C_ZID AND zf_ext.C_SFYX = '1'
		LEFT JOIN (select * from(
						SELECT *,ROW_NUMBER() OVER(partition  BY C_ZF_ID Order by D_BDRQ desc ) AS RowId2
				 FROM DB_YZGL..T_YZGL_ZFBZBD )BD WHERE BD.RowId2=1) ZFBZBD  ON zyxx.C_ZF_ID  = ZFBZBD.C_ZF_ID
		WHERE
		    zyxx.C_ZID =#{ywid,jdbcType=VARCHAR}
		    AND zyxx.C_SFYX = '1'
		) temp where temp.RowId between #{start} and #{end}
    
    参考: https://blog.csdn.net/qq_35893120/article/details/76066609
