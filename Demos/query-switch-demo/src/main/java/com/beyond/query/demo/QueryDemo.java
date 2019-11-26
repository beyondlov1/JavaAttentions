package com.beyond.query.demo;

import com.beyond.query.Page;
import com.beyond.query.QueryChainBuilder;
import com.beyond.query.QueryTemplate;
import com.beyond.query.ResultContainer;
import com.beyond.query.demo.entity.Book;
import com.beyond.query.es.EsQueryChainBuilder;
import com.beyond.query.es.component.SimpleEsQueryComp;
import com.beyond.query.solr.SolrQueryChainBuilder;
import com.beyond.query.solr.component.query.SimpleQueryComp;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.core.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/10/28
 */
@Component
public class QueryDemo {

    @Autowired
    private QueryTemplate<SolrQuery,QueryResponse> solrQueryTemplate;

    @Autowired
    private QueryTemplate<QueryBuilder, SearchResult> esQueryTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void solrQuery() throws Exception {
        QueryChainBuilder<SolrQuery> queryChainBuilder = new SolrQueryChainBuilder();
        SolrQuery query = queryChainBuilder
                .add(new SimpleQueryComp("name", "card"))
                .build();
        ResultContainer<QueryResponse> resultContainer = solrQueryTemplate.query(query);
        List<Book> queryResult = resultContainer.getQueryResult(Book.class);
        System.out.println(objectMapper.writeValueAsString(queryResult));
    }

    public void esQuery() throws Exception {
        QueryChainBuilder<BoolQueryBuilder> queryChainBuilder = new EsQueryChainBuilder();
        BoolQueryBuilder queryBuilder = queryChainBuilder
                .add(new SimpleEsQueryComp("id", "2114"))
                .build();
        ResultContainer<SearchResult> resultContainer = esQueryTemplate.query(queryBuilder);
        Page<Book> page = resultContainer.getQueryResultPage(Book.class, PageRequest.of(1, 2));
        System.out.println(objectMapper.writeValueAsString(page));
    }
}
