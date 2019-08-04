package com.beyond.feigin_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/showBooks")
    public Object showBooks() {
        return bookService.allBooks();
    }
    @RequestMapping("/showBooksByIds")
    public Object showBooksByIds(String ids) {
        return bookService.books(ids);
    }
}
