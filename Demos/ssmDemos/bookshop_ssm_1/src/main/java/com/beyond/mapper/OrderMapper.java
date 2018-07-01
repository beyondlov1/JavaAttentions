package com.beyond.mapper;

import java.util.List;

import com.beyond.entity.Order;

public interface OrderMapper extends BaseMapper<Order> {

    List<Order> selectByBookId(String id);

    List<Order> selectByExchangeBookId(String id);

    Order selectByEitherBookId(String id);

    List<Order> selectByOwnerId(String id);

    List<Order> selectByExchangerId(String id);

    List<Order> selectByExampleBlur(Order order);

}
