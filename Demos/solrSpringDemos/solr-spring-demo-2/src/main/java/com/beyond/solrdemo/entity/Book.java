package com.beyond.solrdemo.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author beyondlov1
 * @date 2019/10/30
 */
@Data
public class Book {
    private String id;
    private String name;
    private BigDecimal price;
}
