package com.beyond.service;

import com.beyond.dao.BookDao;

public class BookService {

	private BookDao bookDao;

	public void addBook() {
		System.out.println("Service addBook");
		bookDao.addBook();
	}

	// spring provide!
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
