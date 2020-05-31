package com.beyond.eureka_client_provider_demo.controller;

import com.beyond.bookserviceapi.Book;
import com.beyond.eureka_client_provider_demo.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
public class BookController2 implements com.beyond.bookserviceapi.BookService{

    @Autowired
    BookService bookService;

    Random random = new Random();

    @Override
    public List<Book> books(@RequestParam("ids") String ids) {
        int sleepTime = random.nextInt(5000);
        System.out.println(sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] split = ids.split(",");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        return bookService.showBooks(strings);
    }

    @Override
    public List<Book> allBooks() {
        int sleepTime = random.nextInt(5000);
        System.out.println(sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String ids = "32,34324,5436,64";
        String[] split = ids.split(",");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
        return bookService.showBooks(strings);
    }

}
