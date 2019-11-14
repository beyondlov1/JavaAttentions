package com.beyond.solrdemo.solr.component;

import org.springframework.data.solr.core.DefaultQueryParser;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Criteria;

/**
 * @author beyondlov1
 * @date 2019/11/11
 */
public class CriteriaQueryComp extends QueryComp {

    private Criteria criteria;

    private DefaultQueryParser defaultQueryParser = new DefaultQueryParser(new SimpleSolrMappingContext());


    public CriteriaQueryComp(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    protected void init() {
        String queryString = defaultQueryParser.createQueryStringFromNode(criteria, null);
        setExpression(queryString);
    }

}
