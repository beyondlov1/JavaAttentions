package com.beyond.eureka_client_consumer_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/consumeBooks")
    public Object consumeBooks(){
        String forObject = restTemplate.getForObject("http://BOOK-SERVICE-PROVIDER/books", String.class);
        System.out.println(forObject);
        return forObject;
    }
}
