package com.beyond.query;

public interface QueryChainBuilder<Query> {
    QueryChainBuilder<Query> add(QueryComponent<Query> component);
    Query build();
}
