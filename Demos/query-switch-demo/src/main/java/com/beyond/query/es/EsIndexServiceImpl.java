package com.beyond.query.es;

import com.beyond.query.demo.entity.Book;
import com.beyond.query.demo.entity.Keyed;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.PutMapping;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenshipeng
 * @date 2019/11/26
 */
@Component
@Slf4j
public class EsIndexServiceImpl implements EsIndexService<Keyed> {
    @Autowired
    private JestClient jestClient;

    @Override
    public void createIndex(String indexName) {
        /**
         * 创建索引
         */
        String mapping = getJoinMapping();
        CreateIndex createIndex = new CreateIndex.Builder(indexName).mappings(mapping).build();
        try {
            JestResult createIndexResult = jestClient.execute(createIndex);
            log.info(createIndexResult.getJsonString());
        } catch (IOException e) {
            log.error("创建索引", e);
        }
    }

    private String getMapping()  {
        InputStream mappingInputStream = this.getClass().getResourceAsStream("author_mapping.json");
        try {
            return IOUtils.toString(mappingInputStream, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("读取author_mapping失败",e);
        }
    }

    private String getJoinMapping()  {
        InputStream joinMappingInputStream = this.getClass().getResourceAsStream("author_join_mapping.json");
        try {
            return IOUtils.toString(joinMappingInputStream, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException("读取author_mapping失败",e);
        }
    }

    @Override
    public void insertIndex(String indexName, Keyed obj) {
        Index index = new Index.Builder(obj).index(indexName).type("_doc").id(String.valueOf(obj.getId())).build();
        try {
            DocumentResult result = jestClient.execute(index);
            log.info(result.getJsonString());
        } catch (IOException e) {
            log.error("插入单个索引信息", e);
        }
    }

    @Override
    public void insertIndex(String indexName, Keyed obj, Integer routing) {
        Index index = new Index.Builder(obj)
                .index(indexName)
                .type("_doc")
                .id(String.valueOf(obj.getId()))
                .setParameter("routing",routing)
                .build();
        try {
            DocumentResult result = jestClient.execute(index);
            log.info(result.getJsonString());
        } catch (IOException e) {
            log.error("插入单个索引信息", e);
        }
    }

    @Override
    public void deleteIndex(String indexName) {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(indexName).build();
        try {
            jestClient.execute(deleteIndex);
        } catch (IOException e) {
            log.error("创建索引", e);
        }
    }
}
