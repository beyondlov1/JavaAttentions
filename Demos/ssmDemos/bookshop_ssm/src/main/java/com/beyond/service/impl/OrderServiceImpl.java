package com.beyond.service.impl;

import java.util.ArrayList;
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

	@Override
	public void addOrder(Order order) {
		orderMapper.add(order);
	}

	@Override
	public void confirmOrder(Order order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void acceptOrder(Order order) {
		// update order permissionstatus
		Order foundOrder = orderMapper.selectByExample(order);
		if ((foundOrder.getOwnerPermissionStatus() != null && foundOrder.getOwnerPermissionStatus() == 1)
				|| (foundOrder.getBook() != null && foundOrder.getBook().getCount() != null
						&& foundOrder.getBook().getCount() < 1)) {
			return;
		}
		foundOrder.setOwnerPermissionStatus(1);
		orderMapper.update(foundOrder);

		// book_count-1
		Book book = bookMapper.selectById(foundOrder.getBook().getId());
		Book exchangeBook = bookMapper.selectById(foundOrder.getExchangeBook().getId());
		if (book.getCount() != null) {
			book.setCount(book.getCount() - 1);
		} else {
			book.setCount(0);
		}
		if (exchangeBook.getCount() != null) {
			exchangeBook.setCount(exchangeBook.getCount() - 1);
		} else {
			exchangeBook.setCount(0);
		}
		bookMapper.update(book);
		bookMapper.update(exchangeBook);
	}

	@Override
	public List<Order> getAcceptOrdersByBookId(String id) {
		Order order = new Order();
		Book book = new Book();
		book.setId(id);
		order.setBook(book);
		order.setOwnerPermissionStatus(1);
		List<Order> list = orderMapper.selectByExampleBlur(order);
		return list;
	}

	@Override
	public List<Order> getAcceptOrdersByExchangeBookId(String id) {
		Order order = new Order();
		Book book = new Book();
		book.setId(id);
		order.setExchangeBook(book);
		order.setOwnerPermissionStatus(1);
		List<Order> list = orderMapper.selectByExampleBlur(order);
		return list;
	}

	@Override
	public List<Order> getAcceptOrdersByEitherBookId(String id) {
		List<Order> acceptOrdersByBookId = getAcceptOrdersByBookId(id);
		List<Order> acceptOrdersByExchangeBookId = getAcceptOrdersByExchangeBookId(id);
		List<Order> list = new ArrayList<>();
		list.addAll(acceptOrdersByBookId);
		list.addAll(acceptOrdersByExchangeBookId);
		return list;
	}

	@Override
	public void cancelOrder(Order order) {
		User user = new User();
		user.setId(CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY));
		order.setBorrower(user);
		Order foundOrder = orderMapper.selectByExample(order);
		orderMapper.delete(foundOrder);
		bookService.modifyBookCount(foundOrder.getBook().getId(), 1);
	}

	@Override
	public void confirmOrderBookRecive(String bookId) {
		// update order
		User user = new User();
		user.setId(CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY));
		Book book = new Book();
		book.setId(bookId);

		Order order = new Order();
		order.setBook(book);
		order.setBorrower(user);
		Order foundOrder = orderMapper.selectByExample(order);

		if (foundOrder != null) {
			foundOrder.setBorrowerConfirmStatus(1);
			orderMapper.update(foundOrder);
			// update book
			Book foundBook = bookService.getBookById(book.getId());
			foundBook.setBorrower(foundOrder.getBorrower());
			bookService.modifyBook(foundBook);
		} else {
			Order order1 = new Order();
			order1.setExchangeBook(book);
			Order foundOrder1 = orderMapper.selectByExample(order1);
			foundOrder1.setOwnerConfirmStatus(1);
			orderMapper.update(foundOrder1);
			// update book
			Book foundBook = bookService.getBookById(book.getId());
			foundBook.setBorrower(userMapper.selectByExample(user));
			bookService.modifyBook(foundBook);
		}

	}
}
