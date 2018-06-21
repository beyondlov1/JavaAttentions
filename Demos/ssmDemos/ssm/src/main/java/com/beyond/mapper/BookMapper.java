package com.beyond.mapper;

import java.util.Set;

import com.beyond.entity.Book;

public interface BookMapper {

	void addBook(Book book);

	void deleteBook(Book book);

	void updateBook(Book book);

	Book selectById(String id);

	Set<Book> selectAll();

}
