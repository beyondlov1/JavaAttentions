package com.beyond.service.impl;

import java.util.List;
import java.util.Map;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Book;
import com.beyond.entity.BookForSql;
import com.beyond.entity.Page;
import com.beyond.exception.IllegalArgumentException;
import com.beyond.proxy.DAOFactory;
import com.beyond.service.BookService;
import com.beyond.utils.IdUtils;

public class BookServiceImpl implements BookService {

	private BaseDao dao = DAOFactory.getInstance().getBaseDao();

	@Override
	public int saveBook(Book book) throws IllegalArgumentException {

		// 1. 验证book中的参数是否符合保存要求
		String name = book.getName();
		double price = book.getPrice();
		if (name == null || "".equals(name) || price == 0 || "".equals(price)) {
			throw new IllegalArgumentException("不合法输入");
		}

		// 2. 设置id
		book.setId(IdUtils.getId());

		return dao.addBean(book);
	}

	public int saveBook(BookForSql book) throws IllegalArgumentException {
		// 1. 验证book中的参数是否符合保存要求
		String name = book.getName();
		double price = book.getPrice();
		if (name == null || "".equals(name) || price == 0 || "".equals(price)) {
			throw new IllegalArgumentException("不合法输入");
		}

		// 2. 设置id
		book.setId(IdUtils.getId());

		return dao.addBean(book);
	}

	@Override
	public int removeBook(String id) {
		return dao.deleteBean(Book.class, id);
	}

	@Override
	public int modifyBook(Book book) {
		return dao.updateBean(book);
	}

	@Override
	public int modifyBook(BookForSql book) {
		return dao.updateBean(book);
	}

	@Override
	public Book findBookById(String id) {
		return dao.selectBean(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		return dao.selectAllBean(Book.class);
	}

	@Override
	public Page<Book> findAll(String url, String currentPageNum) {
		Page<Book> page = null;
		if (currentPageNum != null && !"".equals(currentPageNum)) {
			int totalRecordsCount = dao.selectTotalCount(Book.class);

			// 创建page
			page = new Page<Book>(Integer.parseInt(currentPageNum), totalRecordsCount);
			// 设置page中的content
			List<Book> pageContentList = dao.selectAllBean(Book.class, page.getStartIndex(), page.getPageSize(),
					"createDate", "desc");
			page.setContentList(pageContentList);
			// 设置page中的 url,指向的是要传入currentPageNum的页面
			page.setUrl(url);
		}
		return page;
	}

	@Override
	public int modifyBook(String id, Map<String, Object> map) {
		if (id == null || "".equals(id) || map == null || map.size() == 0) {
			throw new RuntimeException("输入不合法");
		}
		return dao.updateBean(BookForSql.class, id, map);
	}

	@Override
	public long getCountByCondition(String condition) {
		return dao.selectCountByCondition(BookForSql.class, condition);
	}

}
