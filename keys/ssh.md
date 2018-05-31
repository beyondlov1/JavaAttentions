# strust2

作用: 管理action
配置文件: 常量, package, action, result

# hibernate

作用: 管理实体类和数据库中的表的关系, 最终要实现感觉不到数据库存在, 一致在操作实体类
配置文件:   
hibernate.cfg.xml: 配置数据库连接  四大属性, 方言, 数据源, 缓存 etc.
Entity.hbm.xml: 配置映射关系  class, id, property, oneToMany, ManyToOne

# Spring 

作用: 大型工厂, 生产所需对象, 并且利用aop框架代理一些功能, 如事务等功能
配置文件: bean, context:..(用来加载其他文件), aop:..

# struts+spring关键

导入: org.apache.struts  struts2-spring-plugin   2.5.16

maven有坑: 需要exclude(详见tips/ssh)