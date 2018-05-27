package com.beyond.test;

import com.beyond.entity.Book;
import com.beyond.entity.BookForSql;
import com.beyond.entity.Category;
import com.beyond.proxy.DAOFactory;

public class TestConverClass {
	public static void main(String[] args) {

		// ²âÊÔ BookForSql ×ª Book
		BookForSql bfs = new BookForSql();
		bfs.setId("categoryId");
		Book book = DAOFactory.getInstance().convert(Book.class, bfs);
		System.out.println(book.getId() + " " + book.getCategory());

		// ²âÊÔ Book ×ª BookForSql
		Book book1 = null;
		BookForSql bfs1 = null;
		book1 = new Book();
		book1.setId("bookid");
		Category category = new Category();
		category.setId("categoryId");
		book1.setCategory(category);
		bfs1 = DAOFactory.getInstance().convert(BookForSql.class, book1);
		System.out.println(bfs1.getId() + " " + bfs1.getCategoryId());

	}
}
