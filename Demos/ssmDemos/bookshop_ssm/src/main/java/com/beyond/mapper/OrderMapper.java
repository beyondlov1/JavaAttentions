package com.beyond.mapper;

import java.util.List;

import com.beyond.entity.Order;

public interface OrderMapper extends BaseMapper<Order> {

	List<Order> selectByUserId(String id);

	List<Order> selectByBookId(String id);

	List<Order> selectByExampleBlur(Order order);

}
