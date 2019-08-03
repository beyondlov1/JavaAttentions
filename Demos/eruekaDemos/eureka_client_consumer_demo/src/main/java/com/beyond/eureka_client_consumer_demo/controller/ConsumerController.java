package com.beyond.eureka_client_consumer_demo.controller;

import com.beyond.eureka_client_consumer_demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    BookService bookService;

    @Autowired
    String name;

    @RequestMapping("/consumeBooks")
    public Object consumeBooks(){
        System.out.println(name);
        return bookService.consumeBooks();
    }
}
