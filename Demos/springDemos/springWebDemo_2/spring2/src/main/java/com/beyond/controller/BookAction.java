package com.beyond.controller;

import com.beyond.service.BookService;

public class BookAction {

	private BookService bookService;

	public String addBook() {
		System.out.println("BookAction addBook");
		bookService.addBook();
		return null;
	}

	// spring provide!
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
