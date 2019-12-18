package com.beyond.query.demo.entity;

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
public class Book implements Keyed{
    @Field
    private Integer id;
    @Field
    private String name;
    @Field
    private Integer category;
    @Field
    private BigDecimal price;
    private JoinType docType ;

}
