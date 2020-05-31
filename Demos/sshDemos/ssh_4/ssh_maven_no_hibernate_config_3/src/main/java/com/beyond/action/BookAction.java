package com.beyond.action;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.beyond.entity.Book;
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

	private BookServiceImpl bookServiceImpl;

	private UserServiceImpl userServiceImpl;

	public String saveBook() {

		if (uploadFileName != null) {
			// �ϴ��ļ�
			String resultUri = FileUtil.simpleUploadFile(upload, F.UPLOADROOT, uploadFileName);
			book.setUri(resultUri);
			book.setFileName(uploadFileName);
		}

		// �����鼮
		User loginUser = UserUtils.getLoginUser();
		bookServiceImpl.save(loginUser, book);
		return SUCCESS;
	}

	public String removeBook() {
		bookServiceImpl.remove(book);
		return SUCCESS;
	}

	public String modifyBook() {
		bookServiceImpl.modify(book);
		return SUCCESS;
	}

	public String findAllBook() {
		bookServiceImpl.findAll();
		return SUCCESS;
	}

	public String findBookByExample() {
		bookServiceImpl.find(book);
		return SUCCESS;
	}

	// ��¼�û��������鼮
	public String findMyBooks() {
		User loginUser = UserUtils.getLoginUser();
		// sessionʧЧ�����µ�¼
		if (loginUser == null) {
			return INPUT;
		}
		List<Book> userBooks = bookServiceImpl.findBooksByUser(loginUser);
		ActionContext.getContext().put("userBooks", userBooks);
		return SUCCESS;
	}

	// ��ѯ����ĳһ���û��������鼮
	public String findBooksByUser() {

		User foundUser = userServiceImpl.find(User.class, user.getId());
		// �Ҳ����û�
		if (foundUser == null) {
			return INPUT;
		}
		List<Book> userBooks = foundUser.getBooks();
		ActionContext.getContext().put("userBooks", userBooks);
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

	// �ֶ�У��
	/*
	 * public void validateSaveBook() { System.out.println("i am validateSaveBook");
	 * }
	 */
}
