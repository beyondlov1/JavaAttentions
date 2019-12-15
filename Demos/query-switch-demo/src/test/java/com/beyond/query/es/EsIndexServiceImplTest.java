package com.beyond.query.es;

import com.beyond.query.demo.entity.Book;
import com.beyond.query.demo.entity.Author;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsIndexServiceImplTest {

    @Autowired
    EsIndexServiceImpl esIndexService;

    @Autowired
    JestClient jestClient;


    @Test
    void deleteIndex() {
        esIndexService.deleteIndex("author_index");
    }

    @Test
    void createIndex() {
        esIndexService.createIndex("author_index");
    }

    @Test
    void insertIndex() {

        Author author = new Author();
        author.setId(23);
        author.setName("古龙");

        List<Book> books = new ArrayList<>();

        Book book = new Book();
        book.setId(2001);
        book.setName("陆小凤");
        book.setPrice(BigDecimal.valueOf(298));
        book.setCategory(1);

        Book book1 = new Book();
        book1.setId(2002);
        book1.setName("楚留香");
        book1.setPrice(BigDecimal.valueOf(123));
        book1.setCategory(2);

        books.add(book);
        books.add(book1);
        author.setBooks(books);
        esIndexService.insertIndex("author_index",  author);

        Author author2 = new Author();
        author2.setId(23);
        author2.setName("金庸");

        List<Book> books2 = new ArrayList<>();

        Book book3 = new Book();
        book3.setId(7001);
        book3.setName("神雕侠侣");
        book3.setPrice(BigDecimal.valueOf(778));
        book3.setCategory(2);

        Book book4 = new Book();
        book4.setId(7002);
        book4.setName("射雕英雄传");
        book4.setPrice(BigDecimal.valueOf(7222));
        book4.setCategory(2);

        Book book5 = new Book();
        book5.setId(7003);
        book5.setName("天龙八部");
        book5.setPrice(BigDecimal.valueOf(787));
        book5.setCategory(2);

        books2.add(book3);
        books2.add(book4);
        books2.add(book5);
        author2.setBooks(books2);
        esIndexService.insertIndex("author_index",  author2);
    }


    @Test
    public void aggregationTest(){
    }
}