package com.beyond.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.beyond.entity.Book;
import com.beyond.entity.Order;
import com.beyond.entity.User;
import com.beyond.f.F;
import com.beyond.mapper.AuthorMapper;
import com.beyond.mapper.BookMapper;
import com.beyond.mapper.OrderMapper;
import com.beyond.mapper.UserMapper;
import com.beyond.service.BookService;
import com.beyond.service.OrderService;
import com.beyond.utils.CookieUtils;

public class OrderServiceImpl implements OrderService {

    private UserMapper userMapper;
    private BookMapper bookMapper;
    private AuthorMapper authorMapper;
    private OrderMapper orderMapper;

    private BookService bookService;

    // setter
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public void setAuthorMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    //交由前端分类处理
    @Override
    public List<Order> getRelatedOrdersByUser(User user) {
        Order order = new Order();
        order.setOwner(user);
        List<Order> orders = orderMapper.selectByExampleBlur(order);

        Order order1 = new Order();
        order1.setExchanger(user);
        List<Order> orders1 = orderMapper.selectByExampleBlur(order1);

        orders.addAll(orders1);
        return orders;
    }

    @Override
    public Order getAcceptOrdersByEitherBookId(Book book) {
        return orderMapper.selectByEitherBookId(book.getId());
    }

    @Override
    public void removeOrder(Order order) {
        orderMapper.delete(order);
    }

    @Override
    public Order getRelatedOrdersByBook(Book book) {
        return orderMapper.selectByEitherBookId(book.getId());
    }

    @Override
    public void addOrder(Order order) {
        User exchanger = userMapper.selectById(CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY));
        Book book = bookMapper.selectByExample(order.getBook());

        order.setExchanger(exchanger);
        order.setOwner(book.getOwner());
        order.setStatus(0);
        order.setCreateTime(new Timestamp(new Date().getTime()));
        orderMapper.add(order);
    }

    // 我是借书的人: 对方没确认, 对方确认了还没收到, 自己已经收到(未显示)
    @Override
    public List<Order> getNotAcceptedOrders(User user) {
        Order order = new Order();
        order.setExchanger(user);
        order.setStatus(0);
        return orderMapper.selectByExampleBlur(order);
    }

    @Override
    public List<Order> getAcceptedOrders(User user) {
        Order order = new Order();
        order.setExchanger(user);
        order.setStatus(F.ORDER_ACCEPTED);
        return orderMapper.selectByExampleBlur(order);
    }


    // 我是被借书的人: 还没确认和谁换,确认和谁换但还没换|自己和对方都没收到,对方没收到(未显示), 对方确认收到,自己还没收到,自己确认收到交换的书
    @Override
    public List<Order> getToAcceptOrders(User user) {
        Order order = new Order();
        order.setOwner(user);
        order.setStatus(0);
        return orderMapper.selectByExampleBlur(order);
    }

    //对方还没收到,自己收到了或者没收到(显示自己的书)
    @Override
    public List<Order> getExchangeNotOutOrders(User user) {
        Order order = new Order();
        order.setExchanger(user);
        order.setStatus(F.ORDER_ACCEPTED);
        List<Order> orders = orderMapper.selectByExampleBlur(order);

        Order order1 = new Order();
        order1.setExchanger(user);
        order1.setStatus(F.ORDER_EXCHANGER_CONFIRMED);
        List<Order> orders1 = orderMapper.selectByExampleBlur(order1);

        orders.addAll(orders1);
        return orders;
    }

    //对方已经收到(显示自己的书)
    @Override
    public List<Order> getExchangeOutOrders(User user) {
        Order order = new Order();
        order.setExchanger(user);
        order.setStatus(F.ORDER_OWNER_CONFIRMED);
        return orderMapper.selectByExampleBlur(order);
    }

    //自己还没收到对方的书(显示对方的书) 状态为5/1
    @Override
    public List<Order> getExchangeNotInOrders(User user) {
        Order order = new Order();
        order.setOwner(user);
        order.setStatus(F.ORDER_ACCEPTED);
        List<Order> orders = orderMapper.selectByExampleBlur(order);

        Order order1 = new Order();
        order1.setOwner(user);
        order1.setStatus(F.ORDER_EXCHANGER_CONFIRMED);
        List<Order> orders1 = orderMapper.selectByExampleBlur(order1);
        orders.addAll(orders1);
        return orders;
    }

    //自己收到对方的书(显示对方的书)
    @Override
    public List<Order> getExchangeInOrders(User user) {
        Order order = new Order();
        order.setOwner(user);
        order.setStatus(F.ORDER_EXCHANGER_CONFIRMED);
        return orderMapper.selectByExampleBlur(order);
    }

    //双方都收到
    @Override
    public List<Order> getCompletedOrders(User user) {

        Order order = new Order();
        order.setOwner(user);
        order.setStatus(7);
        List<Order> orders = orderMapper.selectByExampleBlur(order);

        Order order1 = new Order();
        order1.setExchanger(user);
        order1.setStatus(7);
        List<Order> orders1 = orderMapper.selectByExampleBlur(order1);

        orders.addAll(orders1);
        return orders;
    }

    @Override
    public List<Order> getOrderByBook(Book book) {
        Order order = new Order();
        order.setBook(book);
        return orderMapper.selectByExampleBlur(order);
    }

    @Override
    public void acceptOrder(Order order) {
        //change status
        Order foundOrder = orderMapper.selectByExample(order);
        if (foundOrder == null) {
            throw new RuntimeException("订单不存在");
        }
        if (foundOrder.getStatus() == null) {
            foundOrder.setStatus(0);
        }
        if (foundOrder.getStatus() >= F.ORDER_ACCEPTED) {
            throw new RuntimeException("已接受此订单, 请不要重复接受");
        }
        foundOrder.setStatus(foundOrder.getStatus() + F.ORDER_ACCEPT_WEIGHT);
        orderMapper.update(foundOrder);

        //change bookCount
        Book book = foundOrder.getBook();
        Book exchangeBook = foundOrder.getExchangeBook();
        book.setCount(book.getCount() - 1);
        exchangeBook.setCount(exchangeBook.getCount() - 1);
        if (book.getCount() < 0) {
            throw new RuntimeException(book.getName() + "/数量不足");
        }
        if (exchangeBook.getCount() < 0) {
            throw new RuntimeException(book.getName() + "/数量不足");
        }
        bookMapper.update(book);
        bookMapper.update(exchangeBook);
    }

    @Override
    public void exchangerConfirm(Order order) {
        //update order
        Order foundOrder = orderMapper.selectByExample(order);
        foundOrder.setStatus(foundOrder.getStatus() + F.ORDER_EXCHANGER_CONFIRM_WEIGHT);
        orderMapper.update(foundOrder);

        //update book
        Book book = foundOrder.getBook();
        book.setBorrower(foundOrder.getExchanger());
        bookMapper.update(book);
    }

    @Override
    public void ownerConfirm(Order order) {
        //update order
        Order foundOrder = orderMapper.selectByExample(order);
        foundOrder.setStatus(foundOrder.getStatus() + F.ORDER_OWNER_CONFIRM_WEIGHT);
        orderMapper.update(foundOrder);

        //update book
        Book exchangeBook = foundOrder.getExchangeBook();
        exchangeBook.setBorrower(foundOrder.getOwner());
        bookMapper.update(exchangeBook);
    }


}
