package com.beyond.demo1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.beyond.entity.Book;
import com.beyond.entity.Owner;
import com.beyond.mapper.OwnerMapper;
import com.beyond.utils.SessionUtils;

/*
 * 一對多
 */
public class Demo4 {

	@Test
	public void testInsert() throws IOException {
		SqlSession session = SessionUtils.getSession();
		OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
		Owner owner = new Owner();
		owner.setUsername("beyond4");
		owner.setPassword("beyondlov1");
		Book book = new Book();
		book.setBookName("testInsert4");
		Set<Book> books = new HashSet<Book>();
		books.add(book);
		owner.setBooks(books);
		ownerMapper.addOwner(owner);

		session.commit();
		session.close();
	}

	@Test
	public void testMapper() throws IOException {
		SqlSession session = SessionUtils.getSession();
		OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
		String ownerId = "402880e863e927310163e92737810000";
		Owner owner = ownerMapper.queryById(ownerId);
		Iterator<Book> it = owner.getBooks().iterator();
		while (it.hasNext()) {
			Book book = it.next();
			System.out.println(book.getBookName());
		}
		session.commit();
		session.close();
	}
}
