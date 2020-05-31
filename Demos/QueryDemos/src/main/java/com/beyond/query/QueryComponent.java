package com.beyond.query;

public interface QueryComponent<Query> {
    Query chain(Query query);
}
