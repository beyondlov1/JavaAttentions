package com.beyond.chat;

import com.beyond.chat.controller.HelloMessageListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class RedisChatRootDemoApplication {

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory,
                                                                       MessageListener messageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(messageListener,new ChannelTopic("hello"));
        return container;
    }

    @Bean
    public MessageListener messageListener(HelloMessageListener helloMessageListener) {
        return new MessageListenerAdapter(helloMessageListener);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisChatRootDemoApplication.class, args);
    }

}
