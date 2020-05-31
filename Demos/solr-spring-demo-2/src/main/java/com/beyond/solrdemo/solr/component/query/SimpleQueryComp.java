package com.beyond.solrdemo.solr.component.query;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.util.ClientUtils;


/**
 * @author beyondlov1
 * @date 2019/10/18
 */
@Data
public class SimpleQueryComp extends AbstractQueryComp {

    public SimpleQueryComp() {
    }

    public SimpleQueryComp(String queryFields, String queryKey) {
        this.queryFields = queryFields;
        this.queryKey = queryKey;
    }

    public SimpleQueryComp(String queryKey, String... queryFields) {
        this.queryFieldArray = queryFields;
        this.queryKey = queryKey;
    }

    @Override
    protected void init(SolrQuery solrQuery) {
        queryKey = processSearchKey(queryKey);
    }

    private String processSearchKey(String searchKey) {
        if (StringUtils.isEmpty(searchKey)) {
            return null;
        }
        String[] multiKeys = searchKey.split(" +");
        StringBuilder processedKeysSb = new StringBuilder();
        for (String key : multiKeys) {
            processedKeysSb.append("+\"");
            processedKeysSb.append(ClientUtils.escapeQueryChars(key));
            processedKeysSb.append("\"");
        }
        if (multiKeys.length > 1) {
            processedKeysSb.append(String.format("\"%s\"", searchKey));
        }
        return processedKeysSb.toString();
    }
}
