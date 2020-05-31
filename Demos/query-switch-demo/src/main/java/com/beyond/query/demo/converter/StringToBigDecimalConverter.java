package com.beyond.query.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 用于 MappingSolrConvertor 添加
 * @author beyondlov1
 * @date 2019/10/29
 */
@Component
@ReadingConverter
public class StringToBigDecimalConverter implements Converter<String, BigDecimal> {
    @Override
    public BigDecimal convert(String source) {
        if (source == null || source.length() < 4) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(source.substring(0,4));
    }
}
