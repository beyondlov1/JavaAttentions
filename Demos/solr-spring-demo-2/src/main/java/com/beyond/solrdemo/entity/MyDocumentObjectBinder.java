package com.beyond.solrdemo.entity;

import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/11/01
 */
public class MyDocumentObjectBinder extends DocumentObjectBinder {

    @Override
    public <T> List<T> getBeans(Class<T> clazz, SolrDocumentList solrDocList) {
        List<T> result = new ArrayList<>(solrDocList.size());
        for (SolrDocument solrDoc : solrDocList) {
            result.add(this.getBean(clazz, solrDoc));
        }
        return result;
    }

    @Override
    public <T> T getBean(Class<T> clazz, SolrDocument solrDoc) {
        if (Book.class.isAssignableFrom(clazz)) {
            Book book = new Book();
            book.setId((String) solrDoc.getFieldValue("id"));
            book.setName((String) solrDoc.getFieldValue("name"));
            if (solrDoc.getFieldValue("price")!=null){
                book.setPrice(new BigDecimal(String.valueOf(solrDoc.getFieldValue("price"))));
            }
            return clazz.cast(book);
        }
        return super.getBean(clazz, solrDoc);
    }
}
