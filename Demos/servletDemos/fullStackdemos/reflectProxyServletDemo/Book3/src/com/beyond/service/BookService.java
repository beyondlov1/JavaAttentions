package com.beyond.service;

import java.util.List;
import java.util.Map;

import com.beyond.entity.Book;
import com.beyond.entity.BookForSql;
import com.beyond.entity.Page;
import com.beyond.exception.IllegalArgumentException;

public interface BookService {

	int saveBook(Book book) throws IllegalArgumentException;

	int saveBook(BookForSql book) throws IllegalArgumentException;

	int removeBook(String id);

	int modifyBook(Book book);

	int modifyBook(BookForSql book);

	int modifyBook(String id, Map<String, Object> map); // 修改某些属性 BookForSql

	Book findBookById(String id);

	@Deprecated
	List<Book> findAll();

	// 分页(url为要将 currentPageNum 传入的页面)
	Page<Book> findAll(String url, String currentPageNum);

	long getCountByCondition(String condition);
}
