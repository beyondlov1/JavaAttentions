package com.example.demo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class TOrder {
    private String id;

    private String userId;

    private Integer amount;

    private List<TOrderDetail> details;

}