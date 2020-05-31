package com.example.demo;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TOrderDetail {
    private String id;

    private String orderId;

    private String goodId;

    private Integer amount;

}