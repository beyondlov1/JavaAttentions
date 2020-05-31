package com.beyond.test;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Category;
import com.beyond.proxy.DAOFactory;

public class Test {
	public static void main(String[] args) {
		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();
		// Book book = new Book();
		// book.setId("10");
		Category category = new Category();
		category.setId("cate44");
		category.setName("Õ∆¿Ì");
		// book.setCategory(category);
		baseDao.addBean(category);
	}
}
