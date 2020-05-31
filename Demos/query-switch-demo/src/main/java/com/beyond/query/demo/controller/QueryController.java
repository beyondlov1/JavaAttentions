package com.beyond.query.demo.controller;

import com.beyond.query.demo.QueryDemo;
import com.beyond.query.demo.entity.Book;
import com.beyond.query.es.EsIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author beyondlov1
 * @date 2019/12/01
 */
@RestController
public class QueryController {

    @Autowired
    QueryDemo queryDemo;

    @Autowired
    EsIndexService esIndexService;

    @RequestMapping("solrQuery")
    public Object solrQuery() throws Exception {
        return queryDemo.solrQuery();
    }

    @RequestMapping("esQuery")
    public Object esQuery() throws Exception {
        return queryDemo.esQuery();
    }

    @RequestMapping("createEsIndex")
    public Object createEsIndex(){
        esIndexService.createIndex("book_index");

        Book book = new Book();
        book.setId(2115);
        book.setName("name");
        book.setPrice(BigDecimal.valueOf(2115));
        esIndexService.insertIndex("book_index", book);


        Book book1 = new Book();
        book1.setId(2114);
        book1.setName("name2");
        book1.setPrice(BigDecimal.valueOf(2114));
        esIndexService.insertIndex("book_index",  book1);
        return "ok";
    }
}
