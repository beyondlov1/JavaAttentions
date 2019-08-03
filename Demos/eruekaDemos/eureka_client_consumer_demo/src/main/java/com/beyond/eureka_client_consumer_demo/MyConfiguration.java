package com.beyond.eureka_client_consumer_demo;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfiguration {
    @Bean
    // 优先解析 @Component @Service @Configuration ..
    // 方法声明的Bean加载顺序无法保证
    // AutoConfigAfter 用于自动配置, 普通的没有用
    @ConditionalOnClass(RestTemplate.class)
    public String name(){
        return "beyond";
    }
}
