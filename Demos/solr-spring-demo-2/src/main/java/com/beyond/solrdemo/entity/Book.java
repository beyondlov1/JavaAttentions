package com.beyond.solrdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

import java.math.BigDecimal;

/**
 * @author beyondlov1
 * @date 2019/10/30
 */
@Data
@NoArgsConstructor
public class Book {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private BigDecimal price;
}
