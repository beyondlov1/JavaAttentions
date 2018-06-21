package com.beyond.demo1;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.beyond.entity.User;

public class Demo1 {

	@Test
	public void test2() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = builder.build(is);
		SqlSession session = sessionFactory.openSession();

		try {
			User user = new User();
			user.setUsername("beyond1");
			user.setPassword("beyondlov1");
			session.insert("com.beyond.insertUser", user);
			session.commit();
		} finally {

			session.close();
		}

	}

	@Test
	public void test1() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory sessionFactory = builder.build(is);
		SqlSession session = sessionFactory.openSession();

		try {
			User user = (User) session.selectOne("com.beyond.selectUser", "beyond");
			System.out.println(user.getPassword());
		} finally {
			session.close();
		}

	}
}
