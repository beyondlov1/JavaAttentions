package com.beyond.query.es;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public interface EsIndexService<T> {
    void createIndex(String indexName,String type);
    void insertIndex(String indexName,String type,T t);
}
