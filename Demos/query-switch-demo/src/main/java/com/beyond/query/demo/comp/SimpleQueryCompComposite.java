package com.beyond.query.demo.comp;

import com.beyond.query.QueryComponent;
import com.beyond.query.QueryComponentUtils;
import com.beyond.query.solr.component.filter.SimpleFilterQueryComp;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/12/02
 */
//@Configurable(autowire = Autowire.BY_TYPE)
public class SimpleQueryCompComposite implements QueryComponent<SolrQuery> {

//    @Autowired
//    private WhateverService whateverService;

    @Override
    public SolrQuery chain(SolrQuery query) {
        List<QueryComponent<SolrQuery>> queryComponents  = new ArrayList<>();
        queryComponents.add(new SimpleFilterQueryComp("name", "ATI Radeon X1900 XTX 512 MB PCIE Video Card"));
        queryComponents.add(new SimpleFilterQueryComp("id", "100-435805"));
//        System.out.println(whateverService.getParam());
        return QueryComponentUtils.chain(queryComponents,query);
    }
}
