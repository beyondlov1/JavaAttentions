package com.beyond.rabbitmqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    @Autowired
    RabbitMqDemoApplication application;

    @RequestMapping("/send")
    public Object send(){
        application.send();
        return "send";
    }
}
