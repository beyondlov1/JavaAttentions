package com.beyond.test;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Book;
import com.beyond.entity.Category;
import com.beyond.proxy.DAOFactory;

public class TestDao1 {
	public static void main(String[] args) {
		for (int i = 0; i < 300; i++) {

			testAdd();
		}

	}

	private static void testUpdate() {
		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();
		Book book = new Book();
		book.setId("1");
		book.setName("³þÁôÏã");
		book.setAuthor("¹ÅÁú");

		Category category = new Category();
		category.setId("cate1");
		category.setName("ÎäÏÀ");
		book.setCategory(category);

		baseDao.updateBean("1", book);
	}

	private static void testAdd() {
		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();

		Book book = new Book();
		book.setId("1");
		book.setName("Â½Ð¡·ï");
		book.setAuthor("¹ÅÁú");

		Category category = new Category();
		category.setId("cate1");
		category.setName("ÎäÏÀ");
		book.setCategory(category);

		// baseDao.addBean(category);
		baseDao.addBean(book);

	}

	private static void testSelect() {
		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();
		Book book = baseDao.selectBean(Book.class, "1");
		System.out.println(book);
		if (book != null) {
			System.out.println(book.getId() + " " + book.getCategory().getName() + " " + book.getName());
		}
	}
}
