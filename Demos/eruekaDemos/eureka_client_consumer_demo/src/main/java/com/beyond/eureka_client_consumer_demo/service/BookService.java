package com.beyond.eureka_client_consumer_demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String consumeBooks(){
        String forObject = restTemplate.getForObject("http://BOOK-SERVICE-PROVIDER/books", String.class);
        System.out.println(forObject);
        return forObject;
    }

    public String error(){
        return "error";
    }
}
