package com.beyond.solrdemo.demo.converter;

import com.beyond.solrdemo.demo.entity.Book;
import com.beyond.solrdemo.solr.converter.AbstractDocumentObjectBinder;
import org.apache.solr.common.SolrDocument;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author beyondlov1
 * @date 2019/11/01
 */
@Component
public class MyDocumentObjectBinder extends AbstractDocumentObjectBinder<Book> {

    @Override
    protected Book getBeanInternal(SolrDocument solrDoc) {
        Book book = new Book();
        book.setId((String) solrDoc.getFieldValue("id"));
        book.setName((String) solrDoc.getFieldValue("name"));
        if (solrDoc.getFieldValue("price")!=null){
            book.setPrice(new BigDecimal(String.valueOf(solrDoc.getFieldValue("price"))));
        }
        return book;
    }

    public static void main(String[] args) {
        MyDocumentObjectBinder binder = new MyDocumentObjectBinder();
        Class<Book> clazz = binder.clazz();
        System.out.println(clazz.getName());
    }
}
