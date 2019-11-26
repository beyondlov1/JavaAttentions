package com.beyond.query.es;

import com.beyond.query.demo.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EsIndexServiceImplTest {

    @Autowired
    EsIndexServiceImpl esIndexService;

    @Test
    void createIndex() {
        esIndexService.createIndex("book_index", "book");
    }

    @Test
    void insertIndex() {
        Book book = new Book();
        book.setId("2115");
        book.setName("name");
        book.setPrice(BigDecimal.valueOf(2115));
        esIndexService.insertIndex("book_index", "book", book);


        Book book1 = new Book();
        book1.setId("2114");
        book1.setName("name2");
        book1.setPrice(BigDecimal.valueOf(2114));
        esIndexService.insertIndex("book_index", "book", book1);
    }
}