package com.beyond.eureka_client_provider_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping("/books")
    public Object books() {
        return "books";
    }

}
