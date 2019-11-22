package com.beyond.solrdemo.solr;

import com.beyond.solrdemo.solr.result.facet.ResultContainer;
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
public class BolrTemplate {

    private SolrClient solrClient;

    private List<Converter> converters = new ArrayList<>();

    public ResultContainer query(String collection, SolrQuery query) throws IOException, SolrServerException {
        assert solrClient != null;
        return query(collection, query, solrClient);
    }

    public ResultContainer query(String collection, SolrQuery query, SolrClient solrClient) throws IOException, SolrServerException {
        ResultContainer resultContainer = new ResultContainer();
        for (Converter converter : converters) {
            resultContainer.addConverter(converter);
        }
        QueryResponse response = solrClient.query(collection, query);
        resultContainer.setResponse(response);
        return resultContainer;
    }

    public void addConverter(Converter converter) {
        converters.add(converter);
    }

    public void setSolrClient(SolrClient solrClient) {
        this.solrClient = solrClient;
    }
}
