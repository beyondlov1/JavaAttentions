package com.beyond.demo1;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.beyond.entity.User;
import com.beyond.mapper.UserMapper;

public class Demo2 {

	@Test
	public void testMapper() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession session = factory.openSession();
		UserMapper userMapper = (UserMapper) session.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("beyond");
		User foundUser = userMapper.queryByExample(user);
		System.out.println(foundUser.getPassword());
	}
}
