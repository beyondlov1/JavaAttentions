package com.beyond.eureka_client_provider_demo.controller;

import com.beyond.eureka_client_provider_demo.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/books")
    public Object books() {
        return bookService.showBooks();
    }

}
