### spring data jpa 整合
实体类上要加上 @Entity @Id @GeneratedValue

### MySql timeZone乱码，不能识别问题
要在url后边加上    ?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8
https://blog.csdn.net/HBella/article/details/99295675

### 配置
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database=mysql
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.url=jdbc:mysql://localhost:3306/btc?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8

### 嵌套的保存
org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.beyond.btc.websocket.KlineStream.data -> com.beyond.btc.websocket.KlineStream$DataBean
要加上@OneToOne 或者 @OneToMany 的注解， 并且设置 cascade 参数为 PERSIST

### UnsupportedOperationException
原因为列表初始化的时候默认了Collections.emptyList() 这个list没有重写add方法导致报错. 说明jpa不是动态判断类型, 而是根据初始化的对象判断类型?

参考: https://segmentfault.com/a/1190000018619089
http://westerly-lzh.github.io/cn/2014/12/JPA-CascadeType-Explaining/
