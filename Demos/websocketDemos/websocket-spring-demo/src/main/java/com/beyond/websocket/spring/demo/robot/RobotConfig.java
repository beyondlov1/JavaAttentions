package com.beyond.websocket.spring.demo.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RobotConfig {
    @Bean
    public BingoNode root(){
        return BingoTreeHelper.getTree();
    }
}
