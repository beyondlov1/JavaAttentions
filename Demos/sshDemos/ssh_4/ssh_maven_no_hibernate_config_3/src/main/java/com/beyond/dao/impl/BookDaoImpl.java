package com.beyond.dao.impl;

import java.util.List;

import com.beyond.entity.Book;

public class BookDaoImpl extends BaseDaoImpl<Book> {
	public Book selectById(String id) {
		return super.selectById(Book.class, id);
	}

	public List<Book> selectAll() {
		return super.selectAll(Book.class);
	}

	@Override
	public Book selectByExample(Book user) {
		return super.selectByExample(user);
	}
}
