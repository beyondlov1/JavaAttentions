package com.beyond.solrdemo.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author beyondlov1
 * @date 2019/10/30
 */
@Data
@NoArgsConstructor
public class BookRaw {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private Float price;
}
