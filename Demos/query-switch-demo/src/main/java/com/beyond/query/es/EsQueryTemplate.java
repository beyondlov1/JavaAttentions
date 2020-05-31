package com.beyond.query.es;

import com.beyond.query.QueryTemplate;
import com.beyond.query.ResultContainer;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
@Component
public class EsQueryTemplate implements QueryTemplate<QueryBuilder, SearchResult> {

    @Autowired
    private JestClient jestClient;

    @Override
    public ResultContainer<SearchResult> queryForResult(QueryBuilder queryBuilder) throws Exception {
        Search search = new Search.Builder(new SearchSourceBuilder().query(queryBuilder).toString()).build();
        SearchResult result = jestClient.execute(search);
        System.out.println(result);

        ResultContainer<SearchResult> resultContainer = new EsResultContainer();
        resultContainer.setResponse(result);
        return resultContainer;
    }

    @Override
    public SearchResult query(QueryBuilder queryBuilder) throws Exception {
        Search search = new Search.Builder(new SearchSourceBuilder().query(queryBuilder).toString()).build();
        return jestClient.execute(search);
    }
}
