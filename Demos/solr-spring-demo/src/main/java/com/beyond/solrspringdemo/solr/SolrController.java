package com.beyond.solrspringdemo.solr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author beyondlov1
 * @date 2019/10/24
 */
@RestController
public class SolrController {

    @Autowired
    private SolrTemplate solrTemplate;

    @RequestMapping("/query")
    public Object query(){
        Criteria criteria = new Criteria("name")
                .contains("name");
        Query query = new SimpleQuery(Criteria.WILDCARD);
        query.addCriteria(criteria);
        ScoredPage<User> userPage = solrTemplate.queryForPage("new_core", query, User.class);
        userPage.get().forEach((System.out::println));
        return userPage;
    }
}
