package com.beyond.solrdemo.solr;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QueryDemoTest {

    @Autowired
    QueryDemo queryDemo;

    @Test
    void query2() throws JsonProcessingException {
        queryDemo.query2();
    }
}