package com.beyond.demo1;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.beyond.entity.Book;
import com.beyond.mapper.BookMapper;

/*
 * 多對一
 */
public class Demo3 {

	@Test
	public void testMapper() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession();

		BookMapper bookMapper = (BookMapper) session.getMapper(BookMapper.class);

		Book book = bookMapper.queryById("402880e863e927310163e92737900003");
		System.out.println(book.getBookName());
		System.out.println(book.getOwner().getUsername());
		session.commit();

		Book book1 = bookMapper.queryById("402880e863e927310163e92737900003");
		System.out.println(book1.getBookName());
		System.out.println(book1.getOwner().getUsername());
		session.close();
	}
}
