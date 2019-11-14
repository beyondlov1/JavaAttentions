package com.beyond.solrdemo.solr;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class QueryDemoTest {

    @Autowired
    QueryDemo queryDemo;

    @Test
    void solrJQuery() throws IOException, SolrServerException {
        queryDemo.solrJQuery();
    }

    @Test
    void solrJBinderQuery() throws IOException, SolrServerException {
        queryDemo.solrJBinderQuery();
    }

    @Test
    void springQuery() throws JsonProcessingException {
        queryDemo.springQuery();
    }

    @Test
    void springFacet() throws JsonProcessingException {
        queryDemo.springFacet();
    }

    @Test
    void solrJFacet() throws IOException, SolrServerException {
        queryDemo.solrJFacet();
    }

    @Test
    void solrCriteriaQuery() throws IOException, SolrServerException {
        queryDemo.solrCriteriaQuery();
    }

    @Test
    void solrJCriteriaQuery() throws IOException, SolrServerException {
        queryDemo.solrJCriteriaQuery();
    }
}