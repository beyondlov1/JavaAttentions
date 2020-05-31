package com.beyond.query.solr.component.query;

import com.beyond.query.solr.component.SolrQueryComponent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/11/15
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractQueryComp implements SolrQueryComponent {

    protected String queryFields;
    protected String[] queryFieldArray;
    protected String queryKey;
    protected String expression;

    protected abstract void init(SolrQuery solrQuery);

    protected SolrQuery chainInternal(SolrQuery query){
        return query;
    }

    @Override
    public SolrQuery chain(SolrQuery solrQuery) {
        init(solrQuery);
        if (StringUtils.isNotBlank(expression)){
            solrQuery.setQuery(expression);
            return solrQuery;
        }
        if (StringUtils.isEmpty(queryKey)) {
            solrQuery.setQuery("*:*");
        } else {
            solrQuery.set("q", queryKey);
            List<String> queryFieldsList = new ArrayList<>();
            if (StringUtils.isNotBlank(queryFields)) {
                queryFieldsList.addAll(Arrays.asList(queryFields.split(",")));
            }
            if (ArrayUtils.isNotEmpty(queryFieldArray)) {
                queryFieldsList.addAll(Arrays.asList(queryFieldArray));
            }
            solrQuery.set("qf", queryFieldsList.toArray(new String[0]));
        }
        return chainInternal(solrQuery);
    }
}
