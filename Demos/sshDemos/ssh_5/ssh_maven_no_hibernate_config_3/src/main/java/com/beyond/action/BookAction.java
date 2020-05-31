package com.beyond.action;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.beyond.entity.Book;
import com.beyond.entity.Page;
import com.beyond.entity.User;
import com.beyond.f.F;
import com.beyond.service.impl.BookServiceImpl;
import com.beyond.service.impl.UserServiceImpl;
import com.beyond.utils.FileUtil;
import com.beyond.utils.UserUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class BookAction extends IOBaseAction implements ModelDriven<Book> {

	private Book book;
	private User user;

	private Integer currentPageNum;

	private BookServiceImpl bookServiceImpl;

	private UserServiceImpl userServiceImpl;

	public String saveBook() throws InterruptedException {

		if (uploadFileName != null) {
			// 上传文件
			String resultUri = FileUtil.simpleUploadFile(upload, F.UPLOADROOT, uploadFileName);
			book.setUri(resultUri);
			book.setFileName(uploadFileName);
		}

		// 保存书籍
		User loginUser = UserUtils.getLoginUser();
		bookServiceImpl.save(loginUser, book);

		for (int i = 0; i < 100; i++) {
			Book book1 = new Book();
			book1.setName("wang");
			bookServiceImpl.save(loginUser, book1);
		}

		return SUCCESS;
	}

	// 登录用户的所有书籍
	public String findMyBooks() {
		User loginUser = UserUtils.getLoginUser();
		// session失效后重新登录
		if (loginUser == null) {
			return INPUT;
		}
		List<Book> userBooks = bookServiceImpl.findBooksByUser(loginUser);
		ActionContext.getContext().put("userBooks", userBooks);
		return SUCCESS;
	}

	// 登录用户的所有书籍
	public String findMyBooksPage() {
		try {
			Page<Book> userBooksPage = bookServiceImpl.findBooksByUserPage(UserUtils.getLoginUser(), currentPageNum);
			ActionContext.getContext().put("userBooksPage", userBooksPage);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 查询其他某一个用户的所有书籍
	public String findBooksByUser() {

		User foundUser = userServiceImpl.find(user.getId());
		// 找不到用户
		if (foundUser == null) {
			return INPUT;
		}
		List<Book> userBooks = foundUser.getBooks();
		ActionContext.getContext().put("userBooks", userBooks);
		return SUCCESS;
	}

	// 查询某一个用户的所有书籍 分页
	public String findBooksByUserPage() {

		try {
			Page<Book> userBooksPage = bookServiceImpl.findBooksByUserPage(user, currentPageNum);
			ActionContext.getContext().put("userBooksPage", userBooksPage);
		} catch (Exception e) {
			e.printStackTrace();
			// return INPUT;
		}
		return SUCCESS;
	}

	// getter setter
	@Override
	public Book getModel() {
		return book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setBookServiceImpl(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}

	public BookServiceImpl getBookServiceImpl() {
		return bookServiceImpl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public Integer getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	// 手动校验
	/*
	 * public void validateSaveBook() { System.out.println("i am validateSaveBook");
	 * }
	 */
}
