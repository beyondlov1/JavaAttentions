package com.beyond.service;

import java.util.List;

import com.beyond.entity.Order;

public interface OrderService {

	void addOrder(Order order);

	void confirmOrder(Order order);

	void acceptOrder(Order order);

	List<Order> getAcceptOrdersByBookId(String id);

	List<Order> getAcceptOrdersByExchangeBookId(String id);

	List<Order> getAcceptOrdersByEitherBookId(String id);

	void cancelOrder(Order order);

	void confirmOrderBookRecive(String bookId);

}
