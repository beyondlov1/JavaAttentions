package com.beyond.query.demo.converter;

import com.beyond.query.demo.entity.Book;
import org.apache.solr.common.SolrDocument;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/10/31
 */
@Component
@ReadingConverter
public class SolrDocumentToBookConverter implements Converter<SolrDocument, Book> {
    @SuppressWarnings("unchecked")
    @Override
    public Book convert(SolrDocument source) {
        Object priceObj = source.getFieldValue("price");
        if (priceObj == null) {
            return null;
        }
        Double price = Double.valueOf(priceObj.toString());
        BigDecimal priceBigDecimal = BigDecimal.valueOf(price);
        priceBigDecimal = priceBigDecimal.setScale(4, RoundingMode.HALF_UP);

        Book book = new Book();
        book.setId((Integer) source.getFieldValue("id"));
        String name = (String) source.getFieldValue("name");
        book.setName(name);
        book.setPrice(priceBigDecimal);
        return book;
    }
}
