package com.beyond.solrdemo.solr;

import com.beyond.solrdemo.entity.Book;
import com.beyond.solrdemo.entity.BookRaw;
import com.beyond.solrdemo.solr.component.QueryComp;
import com.beyond.solrdemo.solr.component.SolrQueryBuilder;
import com.beyond.solrdemo.solr.component.facet.IdFacetQueryComp;
import com.beyond.solrdemo.solr.component.facet.PriceFacetQueryComp;
import com.beyond.solrdemo.solr.component.facet.SimpleFacetQueryComp;
import com.beyond.solrdemo.solr.result.IdFacetResult;
import com.beyond.solrdemo.solr.result.PriceFacetResult;
import com.beyond.solrdemo.solr.result.ResultContainer;
import com.beyond.solrdemo.solr.result.SimpleFacetResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author beyondlov1
 * @date 2019/10/28
 */
@Component
public class QueryDemo {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SolrTemplate solrTemplate2;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private BolrTemplate bolrTemplate;

    public void solrJQuery() throws IOException, SolrServerException {
        SolrQuery query = new SolrQueryBuilder()
                .query(new QueryComp("name", "video"))
                .set("fl", "id,name,price")
                .build();
        QueryResponse book = solrClient.query("techproducts", query);
        List<BookRaw> books = book.getBeans(BookRaw.class);
        System.out.println(objectMapper.writeValueAsString(books));
    }

    public void springQuery() throws JsonProcessingException {
        Query query = new SimpleQuery(Criteria.WILDCARD);
        Criteria criteria = new Criteria("name");
        criteria.contains("video");
        query.addCriteria(criteria);
        query.addProjectionOnField(Field.of("id"));
        query.addProjectionOnField(Field.of("name"));
        query.addProjectionOnField(Field.of("price"));
        Page<Book> book = solrTemplate.queryForPage("techproducts", query, Book.class);
        System.out.println(objectMapper.writeValueAsString(book.getContent()));
    }

    public void springFacet() throws JsonProcessingException {
        FacetQuery query = new SimpleFacetQuery(new Criteria());
        FacetOptions facetOptions = new FacetOptions()
                .addFacetOnField("id")
                .setFacetSort(FacetOptions.FacetSort.COUNT);
        query.setFacetOptions(facetOptions);
        query.addProjectionOnField(Field.of("id"));
        query.addProjectionOnField(Field.of("name"));
        query.addProjectionOnField(Field.of("price"));
        FacetPage<Book> bookFacetPage = solrTemplate.queryForFacetPage("techproducts", query, Book.class);
        System.out.println(objectMapper.writeValueAsString(bookFacetPage));
    }

    public void solrJBinderQuery() throws IOException, SolrServerException {
        SolrQuery query = new SolrQueryBuilder()
                .query(new QueryComp("name", "video"))
                .set("fl", "id,name,price")
                .build();
        QueryResponse response = solrClient.query("techproducts", query);
        List<Book> books = response.getBeans(Book.class);
        System.out.println(objectMapper.writeValueAsString(books));
    }

    public void solrJFacet() throws IOException, SolrServerException {
        SolrQuery query = new SolrQueryBuilder()
                .facet(new IdFacetQueryComp())
                .facet(new PriceFacetQueryComp())
                .facet(new SimpleFacetQueryComp("name"))
                .build();
        ResultContainer resultContainer = bolrTemplate.query("techproducts", query,solrClient);
        Map<Object, IdFacetResult> idFacet = resultContainer.getFacetResultByFieldName("id", IdFacetResult.class);
        System.out.println(objectMapper.writeValueAsString(idFacet));
        Map<Object, PriceFacetResult> priceFacet = resultContainer.getFacetResultByFieldName("price", PriceFacetResult.class);
        System.out.println(objectMapper.writeValueAsString(priceFacet));
        Map<Object, SimpleFacetResult> nameFacet = resultContainer.getFacetResultByFieldName("name", SimpleFacetResult.class);
        System.out.println(objectMapper.writeValueAsString(nameFacet));
    }
}
