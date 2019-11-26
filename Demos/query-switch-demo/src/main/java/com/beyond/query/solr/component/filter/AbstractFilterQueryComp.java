package com.beyond.query.solr.component.filter;

import com.beyond.query.solr.component.SolrQueryComponent;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author beyondlov1
 * @date 2019/11/15
 */
public abstract class AbstractFilterQueryComp implements SolrQueryComponent {

    protected String field;
    protected String filterExpr;
    protected String expression;

    protected abstract void init(SolrQuery query);

    protected SolrQuery chainInternal(SolrQuery query){
        return query;
    }

    @Override
    public SolrQuery chain(SolrQuery query) {
        init(query);
        if (StringUtils.isNotBlank(expression)) {
            query.addFilterQuery(expression);
            return chainInternal(query);
        }
        if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(filterExpr)){
            this.expression = field+":"+filterExpr;
            query.addFilterQuery(expression);
        }
        return chainInternal(query);
    }
}
