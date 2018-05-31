package com.beyond.demo1.entity;

public class BookFactoryInstance {
	public Book getInstance() {
		Book book = new Book();
		book.setId("id");
		book.setName("name");
		book.setPrice(38d);
		return book;
	}
}
