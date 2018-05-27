package recycler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Book;
import com.beyond.entity.BookForSql;
import com.beyond.entity.Category;
import com.beyond.entity.Page;
import com.beyond.entity.User;
import com.beyond.exception.IllegalArgumentException;
import com.beyond.exception.NoLoginException;
import com.beyond.exception.NotFoundUserException;
import com.beyond.exception.NullPasswordException;
import com.beyond.exception.NullUsernameException;
import com.beyond.exception.UsernameRepeatException;
import com.beyond.proxy.DAOFactory;
import com.beyond.service.BookService;
import com.beyond.service.UserService;
import com.beyond.service.impl.BookServiceImpl;
import com.beyond.service.impl.UserServiceImpl;

public class ServiceImpl implements Service {

	// ��¼
	@Override
	public void login(User user) throws ServletException, IOException, NoLoginException, NotFoundUserException {
		UserService us = new UserServiceImpl();
		us.vertifyUser(user);
	}

	// ע���û�
	@Override
	public void signup(User user) throws IOException, ServletException, NullUsernameException, UsernameRepeatException,
			NullPasswordException {
		UserService us = new UserServiceImpl();
		us.saveUser(user);
	}

	// ��ʾ�鼮
	@Override
	public Page<Book> showBook(String currentPageNum) throws ServletException, IOException {
		BookService bs = new BookServiceImpl();
		Page<Book> page = null;
		// ��֤����
		if (currentPageNum == null || "".equals(currentPageNum)) {
			currentPageNum = "1";
		}
		// ��ҳ
		page = bs.findAll(currentPageNum);
		return page;
	}

	// ����鼮
	@Override
	public void addBook(BookForSql book) throws ServletException, IOException, IllegalArgumentException {
		BookService bs = new BookServiceImpl();
		bs.saveBook(book);
	}

	// ��ʾ����鼮ҳ��
	@Override
	public List<Category> getCategoryList() throws ServletException, IOException {
		BaseDao dao = DAOFactory.getInstance().getBaseDao();
		List<Category> categoryList = dao.selectAllBean(Category.class);
		return categoryList;
	}

}
