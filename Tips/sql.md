**timestamp转化为-秒-**

unix_timestamp(timestamp_column)

---

**oracle数据库jdbc的where语句问题**

**问题:** 当某一字段(如id)为char或者varchar2类型时, 用where id="..."的preparedStatement不能获得数据

**解决办法:** 改成varchar类型