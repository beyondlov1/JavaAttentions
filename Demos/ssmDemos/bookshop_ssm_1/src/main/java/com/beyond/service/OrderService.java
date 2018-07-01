package com.beyond.service;

import java.util.List;

import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;

public interface OrderService {


    void addOrder(Order order);

    List<Order> getToAcceptOrders(User user);

    List<Order> getAcceptedOrders(User user);

    List<Order> getCompletedOrders(User user);

    List<Order> getOrderByBook(Book book);

    void acceptOrder(Order order);

    List<Order> getExchangeOutOrders(User user);

    List<Order> getExchangeNotOutOrders(User user);

    List<Order> getExchangeInOrders(User user);

    List<Order> getExchangeNotInOrders(User user);

    List<Order> getNotAcceptedOrders(User user);

    void exchangerConfirm(Order order);

    void ownerConfirm(Order order);

    List<Order> getRelatedOrdersByUser(User user);

    Order getAcceptOrdersByEitherBookId(Book book);

    void removeOrder(Order order);

    Order getRelatedOrdersByBook(Book book);
}
