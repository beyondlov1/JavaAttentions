package com.beyond.rabbitmqdemo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class RabbitMqDemoApplication {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(){
        String text = "hello:"+new Date();
        amqpTemplate.convertAndSend("hello",text);
    }

    @RabbitListener(queues = "hello")
    public void receive(String msg){
        System.out.println(msg);
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqDemoApplication.class, args);
    }

}
