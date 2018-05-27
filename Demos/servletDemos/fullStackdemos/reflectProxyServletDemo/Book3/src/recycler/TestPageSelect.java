package recycler;

import java.util.List;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Book;
import com.beyond.entity.Page;
import com.beyond.proxy.DAOFactory;
import com.beyond.service.BookService;
import com.beyond.service.impl.BookServiceImpl;

public class TestPageSelect {

	public static void main(String[] args) {
		BaseDao dao = DAOFactory.getInstance().getBaseDao();
		List<Book> selectAllBean = dao.selectAllBean(Book.class, 0, 6);
		System.out.println(selectAllBean.get(0).getName());

		BookService bs = new BookServiceImpl();
		Page<Book> findAll = bs.findAll();

		System.out.println(findAll.getContentList().get(1).getName());
	}

}
