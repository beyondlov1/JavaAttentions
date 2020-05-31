package com.beyond.service.impl;

import java.io.File;
import java.util.List;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.User;
import com.beyond.f.F;
import com.beyond.mapper.AuthorMapper;
import com.beyond.mapper.BookMapper;
import com.beyond.mapper.OrderMapper;
import com.beyond.mapper.UserMapper;
import com.beyond.service.BookService;
import com.beyond.utils.CookieUtils;
import com.beyond.utils.RequestResponseBox;

public class BookServiceImpl implements BookService {

	private UserMapper userMapper;
	private BookMapper bookMapper;
	private AuthorMapper authorMapper;
	private OrderMapper orderMapper;

	// setter
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void setBookMapper(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}

	public void setAuthorMapper(AuthorMapper authorMapper) {
		this.authorMapper = authorMapper;
	}

	public void setOrderMapper(OrderMapper orderMapper) {
		this.orderMapper = orderMapper;
	}

	// select
	@Override
	public Book getBookById(String id) {
		Book book = bookMapper.selectById(id);
		return book;
	}

	@Override
	public List<Book> getBookByOwnerId(String id) {
		List<Book> list = bookMapper.selectByOwnerId(id);
		return list;
	}

	@Override
	public List<Book> getBookByAuthorId(String id) {
		return bookMapper.selectByAuthorId(id);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> list = bookMapper.selectAll();
		return list;
	}

	// CUD
	@Override
	public void addBook(Book book) {
		Author author = book.getAuthor();
		User owner = book.getOwner();
		if (author != null) {
			List<Author> authors = authorMapper.selectByExampleBlur(author);
			if (authors != null && authors.size() > 0) {
				book.setAuthor(authors.get(0));
			} else {
				authorMapper.add(author);
				authorMapper.selectByExampleBlur(author);
				book.setAuthor(author);
			}
		}
		if (owner == null) {
			String loginUserId = CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY);
			User foundOwner = userMapper.selectById(loginUserId);
			if (foundOwner != null) {
				book.setOwner(foundOwner);
			}
		}
		if (book.getCount() == null) {
			book.setCount(1);
		}
		bookMapper.add(book);
	}

	@Override
	public void removeBook(Book book) {
		Book foundBook = bookMapper.selectById(book.getId());
		String absolutePath = RequestResponseBox.getRequest().getServletContext().getRealPath("/")
				+ foundBook.getBookUri();
		File file = new File(absolutePath);
		file.delete();
		bookMapper.delete(foundBook);
	}

	@Override
	public void modifyBook(Book book) {
		Author author = book.getAuthor();
		User owner = book.getOwner();
		if (author != null) {
			List<Author> authors = authorMapper.selectByExampleBlur(author);
			if (authors != null && authors.size() > 0) {
				book.setAuthor(authors.get(0));
			} else {
				authorMapper.add(author);
				authorMapper.selectByExampleBlur(author);
				book.setAuthor(author);
			}
		}

		if (owner == null) {
			String loginUserId = CookieUtils.getCookie(F.LOGIN_USER_ID_COOKIE_KEY);
			User foundOwner = userMapper.selectById(loginUserId);
			if (foundOwner != null) {
				book.setOwner(foundOwner);
			}
		}

		bookMapper.update(book);
	}

	@Override
	public List<Book> getBookByBorrowerId(String id) {
		List<Book> list = bookMapper.selectByBorrowerId(id);
		return list;
	}

	@Override
	public User getUserDetail(String id) {
		User foundUser = userMapper.selectById(id);
		return foundUser;
	}

	@Override
	public void modifyBookCount(String id, int offset) {
		Book book = bookMapper.selectById(id);
		book.setCount(book.getCount() + offset);
		bookMapper.update(book);
	}

}
