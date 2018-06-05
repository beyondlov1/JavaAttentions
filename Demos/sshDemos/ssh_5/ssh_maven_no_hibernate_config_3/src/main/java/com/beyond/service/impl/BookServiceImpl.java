package com.beyond.service.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.beyond.dao.impl.BookDaoImpl;
import com.beyond.entity.Book;
import com.beyond.entity.Page;
import com.beyond.entity.User;
import com.beyond.f.F;

@Service
public class BookServiceImpl {

	private BookDaoImpl bookDaoImpl;
	private UserServiceImpl userServiceImpl;

	public void save(User user, Book book) {
		User foundUser = userServiceImpl.find(user);
		foundUser.getBooks().add(book);
		book.setOwner(foundUser);
		userServiceImpl.save(foundUser);
	}

	public List<Book> findBooksByUser(User user) {
		User foundUser = userServiceImpl.find(user);
		List<Book> books = foundUser.getBooks();
		return books;
	}

	// 查询每个用户的书籍 分页
	public Page<Book> findBooksByUserPage(User user, Integer currentPageNum) {

		User foundUser = userServiceImpl.find(user.getId());

		// 找不到用户
		if (foundUser == null) {
			System.out.println("error");
			throw new RuntimeException("user is not exsit");
		}

		Long count = bookDaoImpl.count(user);
		if (currentPageNum == null) {
			currentPageNum = 1;
		}

		Page<Book> page = new Page<Book>(currentPageNum, count, F.RECORDS_PER_PAGE, F.DISPLAY_PAGE_COUNT);
		List<Book> contentList = bookDaoImpl.selectAllByUserPage(foundUser, page);

		// 存储uri
		String uri = ServletActionContext.getRequest().getContextPath();
		uri += ServletActionContext.getRequest().getServletPath();
		uri += "?user.id=" + foundUser.getId();
		page.setUri(uri);

		page.setContentList(contentList);

		return page;
	}

	public BookDaoImpl getBookDaoImpl() {
		return bookDaoImpl;
	}

	public void setBookDaoImpl(BookDaoImpl bookDaoImpl) {
		this.bookDaoImpl = bookDaoImpl;
	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

}
