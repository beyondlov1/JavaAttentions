package com.beyond.solrdemo.solr.component;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author beyondlov1
 * @date 2019/10/18
 */
@Data
public class SortComp implements SolrQueryComponent {

    private String field;
    private SolrQuery.ORDER order;

    public SortComp(String field, SolrQuery.ORDER order) {
        this.field = field;
        this.order = order;
    }

    @Override
    public SolrQuery chain(SolrQuery query) {
        if (StringUtils.isNotBlank(field) && order!=null) {
            query.addSort(field, order);
        }
        return query;
    }
}
