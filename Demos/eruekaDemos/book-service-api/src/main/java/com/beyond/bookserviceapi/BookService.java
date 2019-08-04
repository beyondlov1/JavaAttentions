package com.beyond.bookserviceapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/")
public interface BookService {

    @RequestMapping("/books1")
    List<Book> books(@RequestParam("ids") String ids);

    @RequestMapping("/allBooks1")
    List<Book> allBooks() ;
}

