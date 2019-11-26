package com.beyond.query;

public interface QueryTemplate<Query,Response> {
    ResultContainer<Response> query(Query query) throws Exception;
}
