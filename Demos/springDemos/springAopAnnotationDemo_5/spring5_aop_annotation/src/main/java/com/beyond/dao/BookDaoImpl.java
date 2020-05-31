package com.beyond.dao;

import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {

	public void addBook() {
		System.out.println("Dao addBook");
	}
}
