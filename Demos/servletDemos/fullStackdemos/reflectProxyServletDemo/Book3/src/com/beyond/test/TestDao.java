package com.beyond.test;

import org.junit.Test;

import com.beyond.dao.BaseDao;
import com.beyond.dao.impl.BaseDaoImpl;
import com.beyond.entity.Book;
import com.beyond.entity.Category;
import com.beyond.proxy.DAOFactory;

public class TestDao {

	@Test
	public void testAdd() {
		BaseDao bd = new BaseDaoImpl();
		Book book = new Book();
		book.setId("123");
		bd.addBean(book);
	}

	@Test
	public void testDaoProxy() {
		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();
		Book book = new Book();
		book.setId("10");
		Category category = new Category();
		category.setId("cate12");
		category.setName("cate");
		book.setCategory(category);
		baseDao.addBean(book);
	}

	@Test
	public void testDaoProxy1() {
		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();
		Book book = new Book();
		book.setId("10");
		Category category = new Category();
		category.setId("cate12");
		category.setName("cate");
		book.setCategory(category);
		baseDao.selectBean(Book.class, book.getId());
	}

}
