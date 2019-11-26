package com.beyond.query.solr;

import com.beyond.query.result.SolrResultContainer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/11/09
 */
public class BolrTemplate implements SolrQueryTemplate{

    private SolrClient solrClient;

    private List<Converter> converters = new ArrayList<>();
    private String collection;

    public BolrTemplate(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public SolrResultContainer query(String collection, SolrQuery query) throws IOException, SolrServerException {
        assert solrClient != null;
        return query(collection, query, solrClient);
    }

    @Override
    public SolrResultContainer query(String collection, SolrQuery query, SolrClient solrClient) throws IOException, SolrServerException {
        SolrResultContainer solrResultContainer = new SolrResultContainer();
        for (Converter converter : converters) {
            solrResultContainer.addConverter(converter);
        }
        QueryResponse response = solrClient.query(collection, query);
        solrResultContainer.setResponse(response);
        return solrResultContainer;
    }

    public void addConverter(Converter converter) {
        converters.add(converter);
    }

    public void setSolrClient(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    @Override
    public SolrClient getSolrClient() {
        return solrClient;
    }

    @Override
    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection){
        this.collection = collection;
    }
}
