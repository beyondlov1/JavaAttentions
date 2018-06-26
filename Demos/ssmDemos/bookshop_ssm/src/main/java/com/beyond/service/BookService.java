package com.beyond.service;

import java.util.List;

import com.beyond.entity.Book;
import com.beyond.entity.User;

public interface BookService {

	List<Book> getBookByOwnerId(String id);

	List<Book> getBookByAuthorId(String id);

	Book getBookById(String id);

	List<Book> getAllBooks();

	void addBook(Book book);

	void removeBook(Book book);

	void modifyBook(Book book);

	List<Book> getBookByBorrowerId(String id);

	User getUserDetail(String id);

	void modifyBookCount(String id, int offset);
}
