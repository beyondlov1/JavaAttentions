package recycler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

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

public interface Service {

	void login(User user) throws ServletException, IOException, NoLoginException, NotFoundUserException;

	void signup(User user)
			throws IOException, ServletException, NullUsernameException, UsernameRepeatException, NullPasswordException;

	Page<Book> showBook(String currentPageNum) throws ServletException, IOException;

	void addBook(BookForSql book) throws ServletException, IOException, IllegalArgumentException;

	List<Category> getCategoryList() throws ServletException, IOException;
}
