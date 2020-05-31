package com.beyond.query.es;


import com.beyond.query.demo.entity.Keyed;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
public interface EsIndexService<T> {
    void createIndex(String indexName);
    void insertIndex(String indexName,T t);
    void insertIndex(String indexName, Keyed obj, Integer routing);
    void deleteIndex(String indexName);
}
