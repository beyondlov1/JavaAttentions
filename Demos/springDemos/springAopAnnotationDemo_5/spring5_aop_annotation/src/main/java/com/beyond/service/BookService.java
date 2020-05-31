package com.beyond.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.beyond.dao.BookDao;

@Service("bookService")
public class BookService {

	@Resource(name = "bookDao")
	private BookDao bookDao;

	public void addBook() {
		System.out.println("Service addBook");
		// int i = 10 / 0;
		bookDao.addBook();
	}

	// spring provide!
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
