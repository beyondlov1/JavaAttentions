package com.beyond.query;

public interface QueryTemplate<Query,Response> {
    ResultContainer<Response> queryForResult(Query query) throws Exception;
    Response query(Query query) throws Exception;
}
