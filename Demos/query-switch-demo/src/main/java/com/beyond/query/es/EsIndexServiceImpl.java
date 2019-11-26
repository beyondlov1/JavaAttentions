package com.beyond.query.es;

import com.beyond.query.demo.entity.Book;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
@Component
@Slf4j
public class EsIndexServiceImpl implements EsIndexService<Book> {
    @Autowired
    private JestClient jestClient;

    @Override
    public void createIndex(String indexName, String type) {
        /**
         * 创建索引
         */
        CreateIndex createIndex = new CreateIndex.Builder(indexName).build();
        try {
            jestClient.execute(createIndex);
        } catch (IOException e) {
            log.error("创建索引", e);
        }
    }

    @Override
    public void insertIndex(String indexName, String type, Book book) {
        Index index = new Index.Builder(book).index(indexName).type(type).build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {
            log.error("插入单个索引信息", e);
        }
    }
}
