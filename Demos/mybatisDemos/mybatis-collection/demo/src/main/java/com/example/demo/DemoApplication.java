package com.example.demo;

import com.example.demo.mapper.TOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@SpringBootApplication
@RestController
public class DemoApplication {

    @Autowired
    TOrderMapper orderMapper;

    @RequestMapping("/hello")
    public Object hello(){
        return orderMapper.selectByPrimaryKey("2");
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
