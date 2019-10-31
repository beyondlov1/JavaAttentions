package com.beyond.solrdemo.solr;

import com.beyond.solrdemo.entity.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Component;

/**
 * @author beyondlov1
 * @date 2019/10/28
 */
@Component
public class QueryDemo {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SolrTemplate solrTemplate2;

    @Autowired
    private ObjectMapper objectMapper;

    public void query2() throws JsonProcessingException {
        Query query = new SimpleQuery(Criteria.WILDCARD);
        Criteria criteria = new Criteria("name");
        criteria.contains("video");
        query.addCriteria(criteria);
        query.addProjectionOnField(Field.of("id"));
        query.addProjectionOnField(Field.of("name"));
        query.addProjectionOnField(Field.of("price"));
        Page<Book> book = solrTemplate.queryForPage("techproducts", query, Book.class);
        System.out.println(objectMapper.writeValueAsString(book.getContent()));
    }
}
