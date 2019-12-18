package com.beyond.query.es;

import com.beyond.query.demo.entity.Book;
import com.beyond.query.demo.entity.Author;
import com.beyond.query.demo.entity.JoinType;
import io.searchbox.client.JestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsIndexServiceImplTest {

    @Autowired
    EsIndexServiceImpl esIndexService;

    @Autowired
    JestClient jestClient;

    private static final String INDEX_NAME = "author_join_index";


    @Test
    void deleteIndex() {
        esIndexService.deleteIndex(INDEX_NAME);
    }

    @Test
    void createIndex() {
        esIndexService.createIndex(INDEX_NAME);
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
//        author.setBooks(books);
        esIndexService.insertIndex(INDEX_NAME, author);

        Author author2 = new Author();
        author2.setId(24);
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
//        author2.setBooks(books2);
        esIndexService.insertIndex(INDEX_NAME, author2);


        for (Book book2 : books) {
            book2.setDocType(JoinType.bookType(author.getId()));
            esIndexService.insertIndex(INDEX_NAME,book2,author.getId());
        }
        for (Book book2 : books2) {
            book2.setDocType(JoinType.bookType(author2.getId()));
            esIndexService.insertIndex(INDEX_NAME,book2,author2.getId());
        }
    }


    @Test
    public void aggregationTest() {
    }
}