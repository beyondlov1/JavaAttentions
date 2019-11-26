package com.beyond.query;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResultContainer<R> {
    void addResultHandler(ResultHandler resultHandler);
    <T> List<T> getQueryResult(Class<T> clazz);
    <T> Page<T> getQueryResultPage(Class<T> clazz, Pageable pageable);
    R getResponse();
    void setResponse(R response);
}
