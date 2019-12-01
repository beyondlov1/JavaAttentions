package com.beyond.query.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryDemoTest {

    @Autowired
    QueryDemo queryDemo;

    @Test
    void solrQuery() throws Exception {
        queryDemo.solrQuery();
    }

    @Test
    void esQuery() throws Exception {
//        queryDemo.esQuery();
    }
}