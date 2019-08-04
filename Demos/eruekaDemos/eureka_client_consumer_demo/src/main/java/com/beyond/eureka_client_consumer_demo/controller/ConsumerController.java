package com.beyond.eureka_client_consumer_demo.controller;

import com.beyond.eureka_client_consumer_demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    BookService bookService;

    @Autowired
    String name;

    @RequestMapping("/consumeBooks")
    public Object consumeBooks(String id){
        System.out.println(name);
        return bookService.consumeBooks(id);
    }

    @RequestMapping("/consumeBooksAll")
    public Object consumeBooksAll(List<String> ids){
        System.out.println(name);
        return bookService.consumeBooksAll(ids);
    }
}
