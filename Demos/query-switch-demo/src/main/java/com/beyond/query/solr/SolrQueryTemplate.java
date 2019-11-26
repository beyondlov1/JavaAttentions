package com.beyond.query.solr;

import com.beyond.query.QueryTemplate;
import com.beyond.query.ResultContainer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public interface SolrQueryTemplate extends QueryTemplate<SolrQuery, QueryResponse> {

    @Override
    default  ResultContainer<QueryResponse> query(SolrQuery query) throws Exception {
        return query(getCollection(),query,getSolrClient());
    }

    ResultContainer<QueryResponse> query(String collection, SolrQuery query, SolrClient solrClient) throws IOException, SolrServerException;

    SolrClient getSolrClient();

    String getCollection();
}
