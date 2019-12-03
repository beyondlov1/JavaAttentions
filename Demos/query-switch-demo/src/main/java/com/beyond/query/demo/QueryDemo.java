package com.beyond.query.demo;

import com.beyond.query.Page;
import com.beyond.query.QueryTemplate;
import com.beyond.query.ResultContainer;
import com.beyond.query.demo.comp.SimpleQueryCompComposite;
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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author beyondlov1
 * @date 2019/10/28
 */
@Component
public class QueryDemo implements BeanFactoryAware {

    @Autowired
    private QueryTemplate<SolrQuery, QueryResponse> solrQueryTemplate;

    @Autowired
    private QueryTemplate<QueryBuilder, SearchResult> esQueryTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    BeanFactory beanFactory;

    public Object solrQuery() throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        SolrQuery query = new SolrQueryChainBuilder()
                .add(new SimpleQueryComp("name", "card"))
                .add(new SimpleQueryCompComposite())
                .build();
        stopWatch.stop();

        System.out.println("time:"+stopWatch.getLastTaskTimeMillis());

        ResultContainer<QueryResponse> resultContainer = solrQueryTemplate.queryForResult(query);
        return resultContainer.getQueryResult(Book.class);
    }

    public Object esQuery() throws Exception {
        BoolQueryBuilder queryBuilder = new EsQueryChainBuilder()
                .add(new SimpleEsQueryComp("id", "2114"))
                .build();
        ResultContainer<SearchResult> resultContainer = esQueryTemplate.queryForResult(queryBuilder);
        Page<Book> page = resultContainer.getQueryResultPage(Book.class, PageRequest.of(1, 2));
        System.out.println(objectMapper.writeValueAsString(page));
        return page;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
