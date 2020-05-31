package com.beyond.query.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QueryDemoTest {

    @Autowired
    QueryDemo queryDemo;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void solrQuery() throws Exception {
        Object o = queryDemo.solrQuery();
        System.out.println(objectMapper.writeValueAsString(o));
    }

    @Test
    void esQuery() throws Exception {
//        queryDemo.esQuery();
    }
}