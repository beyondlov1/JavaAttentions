package com.beyond.mapper;

import java.util.List;

import com.beyond.entity.Book;

public interface BookMapper extends BaseMapper<Book> {

	List<Book> selectByExampleBlur(Book book);

	List<Book> selectByAuthorId(String id);

	List<Book> selectByOwnerId(String id);

	List<Book> selectByBorrowerId(String id);

	void cancelBorrowBook(String id);

}
