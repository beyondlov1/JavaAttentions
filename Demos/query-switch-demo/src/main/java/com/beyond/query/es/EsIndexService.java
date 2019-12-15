package com.beyond.query.es;


/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public interface EsIndexService<T> {
    void createIndex(String indexName);
    void insertIndex(String indexName,T t);
    void deleteIndex(String indexName);
}
