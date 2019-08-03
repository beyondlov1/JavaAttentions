package com.beyond.eureka_client_provider_demo;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    public String showBooks(){
        return "books";
    }
}
