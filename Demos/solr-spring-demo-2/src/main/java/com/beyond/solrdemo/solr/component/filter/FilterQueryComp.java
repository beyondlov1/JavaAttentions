package com.beyond.solrdemo.solr.component.filter;

import com.beyond.solrdemo.solr.component.SolrQueryComponent;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.solr.core.query.Criteria;

/**
 * @author beyondlov1
 * @date 2019/10/18
 */
@Data
public class FilterQueryComp implements SolrQueryComponent {
    private String field;
    private String filterExpr;
    private String expression;

    public FilterQueryComp() {
    }

    public FilterQueryComp(String field, Number filterExpr) {
        this(field, filterExpr.toString());
    }

    public FilterQueryComp(String field, String filterExpr) {
        this.field = field;
        this.filterExpr = filterExpr;
    }

    public FilterQueryComp(String expression) {
        this.expression = expression;
    }

    protected void init(){
        // for inherit
    }

    @Override
    public SolrQuery chain(SolrQuery query) {
        init();
        if (StringUtils.isNotBlank(field) && StringUtils.isNotBlank(filterExpr)){
            this.expression = field+":"+filterExpr;
        }
        if (StringUtils.isNotBlank(expression)) {
            query.addFilterQuery(expression);
        }
        return query;
    }
}
