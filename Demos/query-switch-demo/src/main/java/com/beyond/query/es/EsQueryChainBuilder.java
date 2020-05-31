package com.beyond.query.es;

import com.beyond.query.QueryChainBuilder;
import com.beyond.query.QueryComponent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public class EsQueryChainBuilder implements QueryChainBuilder<BoolQueryBuilder> {

    private List<QueryComponent<BoolQueryBuilder>> components = new ArrayList<>();

    public EsQueryChainBuilder() {
    }

    @Override
    public QueryChainBuilder<BoolQueryBuilder> add(QueryComponent<BoolQueryBuilder> component) {
        components.add(component);
        return this;
    }

    @Override
    public BoolQueryBuilder build() {
        BoolQueryBuilder mainQueryBuilder = QueryBuilders.boolQuery();
        for (QueryComponent<BoolQueryBuilder> component : components) {
            mainQueryBuilder = component.chain(mainQueryBuilder);
        }
        return mainQueryBuilder;
    }
}
