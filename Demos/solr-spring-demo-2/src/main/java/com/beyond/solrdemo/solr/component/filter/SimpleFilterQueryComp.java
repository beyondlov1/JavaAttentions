package com.beyond.solrdemo.solr.component.filter;

import lombok.Data;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author beyondlov1
 * @date 2019/10/18
 */
@Data
public class SimpleFilterQueryComp extends AbstractFilterQueryComp {

    private String field;
    private String filterExpr;
    private String expression;

    public SimpleFilterQueryComp() {
    }

    public SimpleFilterQueryComp(String field, Number filterExpr) {
        this(field, filterExpr.toString());
    }

    public SimpleFilterQueryComp(String field, String filterExpr) {
        this.field = field;
        this.filterExpr = filterExpr;
    }

    public SimpleFilterQueryComp(String expression) {
        this.expression = expression;
    }


    @Override
    protected void init(SolrQuery query) {
        // do nothing
    }
}
