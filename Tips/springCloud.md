### spring cloud config
在于eureka 结合后会先注册服务, 发现config之后刷新配置, 如果config-server服务中有注册eureka的配置,会覆盖现有配置, 重新注册
不过这个server 刷新git的策略, 什么时候刷新还不大清楚

### spring cloud config 客户端刷新
客户端配置文件中添加:
management.endpoints.web.exposure.include=refresh
客户端: http://localhost:1120/actuator/refresh

### spring cloud bus

与配置中心结合时, spring-boot 2.0 修改了刷新的端口, 由原来的 **bus/refresh** 改为了 **actuator/bus-refresh**

并且配置中要添加

```

```

