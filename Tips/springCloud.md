### spring cloud config
在于eureka 结合后会先注册服务, 发现config之后刷新配置, 如果config-server服务中有注册eureka的配置,会覆盖现有配置, 重新注册
不过这个server 刷新git的策略, 什么时候刷新还不大清楚

### spring cloud config 客户端刷新
客户端配置文件中添加:
management.endpoints.web.exposure.include=refresh
客户端: http://localhost:1120/actuator/refresh
