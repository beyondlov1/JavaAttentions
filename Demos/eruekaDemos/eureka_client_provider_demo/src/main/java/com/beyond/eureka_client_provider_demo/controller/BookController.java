package com.beyond.eureka_client_provider_demo.controller;

import com.beyond.bookserviceapi.Book;
import com.beyond.eureka_client_provider_demo.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController("/controller1")
public class BookController {

    @Autowired
    BookService bookService;

    Random random = new Random();

    @RequestMapping("/books")
    public List<Book> books(String ids) throws InterruptedException {
        String[] split = ids.split(",");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        return bookService.showBooks(strings);
    }

    @RequestMapping("/allBooks")
    public List<Book> allBooks() {
        String ids = "32,34324,5436,64";
        String[] split = ids.split(",");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        return bookService.showBooks(strings);
    }

}
