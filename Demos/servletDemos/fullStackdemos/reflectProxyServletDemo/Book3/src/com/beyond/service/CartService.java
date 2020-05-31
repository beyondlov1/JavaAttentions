package com.beyond.service;

import java.util.Map;

import com.beyond.entity.Book;

public interface CartService {

	int putInCart(Book book);

	Map<String, String> findAllCart();
}
