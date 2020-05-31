package com.beyond.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beyond.dao.impl.BookDaoImpl;
import com.beyond.dao.impl.UserDaoImpl;
import com.beyond.entity.Book;
import com.beyond.entity.User;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book> {

	private BookDaoImpl bookDaoImpl;
	private UserDaoImpl userDaoImpl;

	public void save(User user, Book book) {
		User foundUser = userDaoImpl.selectByExample(user);
		foundUser.getBooks().add(book);
		book.setOwner(foundUser);
		userDaoImpl.add(foundUser);
	}

	public List<Book> findBooksByUser(User user) {
		User foundUser = userDaoImpl.selectByExample(user);
		List<Book> books = foundUser.getBooks();
		return books;
	}

	public List<Book> findAll() {
		return super.findAll(Book.class);
	}

	public BookDaoImpl getBookDaoImpl() {
		return bookDaoImpl;
	}

	public void setBookDaoImpl(BookDaoImpl bookDaoImpl) {
		this.bookDaoImpl = bookDaoImpl;
	}

	public UserDaoImpl getUserDaoImpl() {
		return userDaoImpl;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}

}
