### spring cloud config
在于eureka 结合后会先注册服务, 发现config之后刷新配置, 如果config-server服务中没有注册eureka的配置, 会导致服务不能注册. 解决办法是在git上放一份带有服务发现的配置.
但是感觉不应该是这么用的吧, 搞不懂
