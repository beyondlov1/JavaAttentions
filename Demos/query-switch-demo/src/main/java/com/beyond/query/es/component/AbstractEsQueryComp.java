package com.beyond.query.es.component;

import com.beyond.query.QueryComponent;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public abstract class AbstractEsQueryComp implements QueryComponent<BoolQueryBuilder> {
    @Override
    public BoolQueryBuilder chain(BoolQueryBuilder boolQueryBuilder) {
        return boolQueryBuilder.must(getQueryBuilder());
    }

    protected abstract QueryBuilder getQueryBuilder();
}
