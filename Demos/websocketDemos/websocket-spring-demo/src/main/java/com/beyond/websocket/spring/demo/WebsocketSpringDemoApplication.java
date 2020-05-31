package com.beyond.websocket.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class WebsocketSpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketSpringDemoApplication.class, args);
    }

}
