package com.beyond.query.demo.entity;

import lombok.Data;

@Data
public class JoinType {
    private String name;
    private Integer parent;

    public static JoinType bookType(Integer parent){
        JoinType joinType = new JoinType();
        joinType.name = "books";
        joinType.parent = parent;
        return joinType;
    }
}