# JDBC

## 名称

java database connectivity

## 作用

链接数据，并对数据进行操作，增删改查

JDBC是一种规范，由数据库厂商来实现这种规范

1. 建立连接
2. 发送sql语句
3. 获得运行结果

## 使用步骤

1. 导入驱动包
2. 注册驱动
3. 获得连接
四种方法： 
Class.forName()
DriverManager.getConnection(url,username,password);
Driver driver = new OracleDriver();
driver.connect()

4. 获得statment/preparedStatement
优势：提高运行效率，安全，方便开发
5. executeUpdate()/executeQuery()
6. 关闭连接，释放资源

## 模糊查询

拼接字符串 关键是 where 1=1

## DAO-DTO-ENTITY

Date Access Object

Data Transfer Object 层与层之间传输数据,不参与数据表映射

ENTItY

## 获取元数据

就是获取一些有关数据库，connection，driver之类的一些数据

DatabaseMetaData 根据connection获取

ParameterMetaData 根据preparedStatement获取。
获取到参数类型，参数个数等信息。

ResultSetMetaData

## 泛型DAO

getDeclaredField()

setAccessible();

## DataSource

Hikari

DataSource.getConnection();

## 事务

conn.setAutoCommit(false)

conn.rollback()

conn.commit()

## ThreadLocal<Connection>

## CLOB/BLOB

Character Large Object
Byte Large Object

CLOB: 
存入：
Clob clob = conn.createClob;
ps.setClob(1,clob);

Reader reader = StringReader()/FileReader()
ps.setClob(2,reader);

获取：
Reader reader = rs.getCharacterStream(1)
reader.read(charArray)...


Blob:
存入：
InputStream is = ...
ps.setBlob(1,is)

获取：
OutputStream os = ps.getBinaryStream()


## batch

statememt.addBatch(sql);






