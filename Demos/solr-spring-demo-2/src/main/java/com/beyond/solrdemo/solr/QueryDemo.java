package com.beyond.solrdemo.solr;

import com.beyond.solrdemo.entity.Book;
import com.beyond.solrdemo.entity.BookRaw;
import com.beyond.solrdemo.solr.component.query.CriteriaQueryComp;
import com.beyond.solrdemo.solr.component.query.SimpleQueryComp;
import com.beyond.solrdemo.solr.component.SolrQueryBuilder;
import com.beyond.solrdemo.solr.component.facet.IdFacetQueryComp;
import com.beyond.solrdemo.solr.component.facet.PriceFacetQueryComp;
import com.beyond.solrdemo.solr.component.facet.SimpleFacetQueryComp;
import com.beyond.solrdemo.solr.component.filter.CriteriaFilterQueryComp;
import com.beyond.solrdemo.solr.result.facet.IdFacetResult;
import com.beyond.solrdemo.solr.result.facet.PriceFacetResult;
import com.beyond.solrdemo.solr.result.facet.ResultContainer;
import com.beyond.solrdemo.solr.result.facet.SimpleFacetResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.DefaultQueryParser;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                .query(new SimpleQueryComp("name", "video"))
                .set("fl", "id,name,price")
                .build();
        QueryResponse book = solrClient.query("techproducts", query);
        List<BookRaw> bookRaws = book.getBeans(BookRaw.class);
        List<Book> books = book.getBeans(Book.class);
        System.out.println(objectMapper.writeValueAsString(bookRaws));
        System.out.println(objectMapper.writeValueAsString(books));
    }

    public void springQuery() throws JsonProcessingException {
        Query query = new SimpleQuery(Criteria.WILDCARD);
        Criteria criteria = Criteria.where("name");
        criteria.fuzzy("我们都",1);
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
                .query(new SimpleQueryComp("name", "video"))
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
        ResultContainer resultContainer = bolrTemplate.query("techproducts", query, solrClient);
        Map<Object, IdFacetResult> idFacet = resultContainer.getFacetResultByFieldName("id", IdFacetResult.class);
        System.out.println(objectMapper.writeValueAsString(idFacet));
        Map<Object, PriceFacetResult> priceFacet = resultContainer.getFacetResultByFieldName("price", PriceFacetResult.class);
        System.out.println(objectMapper.writeValueAsString(priceFacet));
        Map<Object, SimpleFacetResult> nameFacet = resultContainer.getFacetResultByFieldName("name", SimpleFacetResult.class);
        System.out.println(objectMapper.writeValueAsString(nameFacet));
    }

    public void solrCriteriaQuery() throws IOException, SolrServerException {
        DefaultQueryParser defaultQueryParser = new DefaultQueryParser(new SimpleSolrMappingContext());
        Query query = new SimpleQuery(Criteria.WILDCARD);
        Criteria criteria = Criteria.where("name");
        criteria.contains("video");
        query.addCriteria(criteria);
        SolrQuery solrQuery = defaultQueryParser.doConstructSolrQuery(query, Book.class);
        QueryResponse response = solrClient.query("techproducts",solrQuery);
        List<Book> books = response.getBeans(Book.class);
        System.out.println(objectMapper.writeValueAsString(books));
    }

    public void solrJCriteriaQuery() throws IOException, SolrServerException {
        Criteria criteria = Criteria.where("id").is("100-435805").and("name").is("video");
        Criteria criteria2 = Criteria.where("price").greaterThan(100);
        Criteria queryCriteria = Criteria.where("name").contains("card");
        SolrQuery solrQuery = new SolrQueryBuilder(Book.class)
                .query(new CriteriaQueryComp(queryCriteria))
                .filter(new CriteriaFilterQueryComp(criteria))
                .filter(new CriteriaFilterQueryComp(criteria2))
                .build();

        ResultContainer resultContainer = bolrTemplate.query("techproducts", solrQuery, solrClient);
        List<Book> books1 = resultContainer.getQueryResult(Book.class);
        System.out.println(objectMapper.writeValueAsString(books1));

        QueryResponse response = solrClient.query("techproducts", solrQuery);
        List<Book> books = response.getBeans(Book.class);
        System.out.println(objectMapper.writeValueAsString(books));
    }

    public static void main(String[] args) {

        Criteria criteria1E = Criteria.where("e").is("e").and("g").is("g");
        criteria1E.and("h").is("h");

        Criteria criteria = Criteria.where(Criteria.WILDCARD);
        criteria.is("c")
                .and("b").in("bcc", "fege")
                .and("d").is("d")
                .or(criteria1E)
                .or(Criteria.where("f").is("f"));

        Criteria criteria1 = Criteria.where("ccc");
        criteria1.is("cc");
        criteria.or(criteria1);

        StringBuilder sb = new StringBuilder();
        Node parent = criteria.getParent();
        while (parent != null) {
            sb.append(getNodeStr(parent));
            parent = parent.getParent();
        }

        DefaultQueryParser defaultQueryParser = new DefaultQueryParser(new SimpleSolrMappingContext());
        String queryString = defaultQueryParser.createQueryStringFromNode(criteria, null);
        System.out.println(queryString);
    }

    private static String getNodeStr(Node node) {
        if (node instanceof Crotch) {
            Collection<Criteria> siblings = node.getSiblings();
            StringBuilder sb = new StringBuilder();
            sb.append(node.isOr() ? " or " : " and " );
            sb.append("(");
            for (Criteria sibling : siblings) {
                sb.append(getNodeStr(sibling));
            }
            sb.append(")");
            return sb.toString();
        } else if (node instanceof Criteria) {
            StringBuilder sb = new StringBuilder();
            sb.append(node.isOr() ? " or " : " and ");
            sb.append(((Criteria) node).getField());
            sb.append(":");
            sb.append(((Criteria) node).getPredicates());
            return sb.toString();
        }
        return null;
    }

    private static void concat(Node sibling, Node root, StringBuilder stringBuilder) {
        Node parent = sibling.getParent();
        if (parent != root && parent != null) {
            concat(parent, root, stringBuilder);
        } else {
            System.out.println(sibling);
        }
    }

    private static void printCriteria(Criteria criteria) {
        if (criteria instanceof Crotch) {
            Collection<Criteria> siblings = criteria.getSiblings();
            System.out.println("---- " + (criteria.isOr() ? "or" : "and") + " ----");
            for (Criteria sibling : siblings) {
                printCriteria(sibling);
            }
        } else if (criteria != null) {
            Field field = criteria.getField();
            Set<Criteria.Predicate> predicates = criteria.getPredicates();
            for (Criteria.Predicate predicate : predicates) {
                String key = predicate.getKey();
                Object value = predicate.getValue();
                System.out.println("---- sibling ----");
                System.out.println(field);
                System.out.println(key);
                System.out.println(value);
            }
        }
    }
}
