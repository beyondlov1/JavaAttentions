package com.beyond.query.demo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author beyondlov1
 * @date 2019/12/14
 */
@Data
public class Author {
    private Integer id;
    private String name;
    private List<Book> books;
}
