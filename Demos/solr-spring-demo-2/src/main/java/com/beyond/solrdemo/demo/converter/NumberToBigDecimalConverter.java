package com.beyond.solrdemo.demo.converter;

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
public class NumberToBigDecimalConverter implements Converter<Number, BigDecimal> {
    @Override
    public BigDecimal convert(Number source) {
        if (source == null) {
            return null;
        }
        return new BigDecimal(source.toString());
    }
}
