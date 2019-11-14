package com.beyond.solrdemo.solr.component;

import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.util.ClientUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author beyondlov1
 * @date 2019/10/18
 */
@Data
public class QueryComp implements SolrQueryComponent {
    private String queryFields;
    private String[] queryFieldArray;
    private String queryKey;
    private String expression;

    public QueryComp() {
    }

    public QueryComp(String queryFields, String queryKey) {
        this.queryFields = queryFields;
        this.queryKey = queryKey;
    }

    public QueryComp(String queryKey, String... queryFields) {
        this.queryFieldArray = queryFields;
        this.queryKey = queryKey;
    }

    protected void init(){
        // for inherit
    }


    @Override
    public SolrQuery chain(SolrQuery solrQuery) {
        init();
        if (StringUtils.isEmpty(queryKey)) {
            solrQuery.setQuery("*:*");
        } else {
            String[] multiKeys = queryKey.split(" +");
            StringBuilder processedKeysSb = new StringBuilder();
            for (String key : multiKeys) {
                processedKeysSb.append("+\"");
                processedKeysSb.append(ClientUtils.escapeQueryChars(key));
                processedKeysSb.append("\"");
            }
            if (multiKeys.length > 1) {
                processedKeysSb.append(String.format("\"%s\"", queryKey));
            }

            solrQuery.set("q", processedKeysSb.toString());
            List<String> queryFieldsList = new ArrayList<>();
            if (StringUtils.isNotBlank(queryFields)) {
                queryFieldsList.addAll(Arrays.asList(queryFields.split(",")));
            }
            if (ArrayUtils.isNotEmpty(queryFieldArray)) {
                queryFieldsList.addAll(Arrays.asList(queryFieldArray));
            }
            solrQuery.set("qf", queryFieldsList.toArray(new String[0]));
        }
        return solrQuery;
    }


    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
