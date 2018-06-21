package com.beyond.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.entity.User;
import com.beyond.mapper.UserMapper;

public class Demo1 {

	@Test
	public void test1() throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper bean = (UserMapper) context.getBean("userMapper");
		System.out.println(bean);
		User user = new User();
		user.setUsername("beyond");
		User foundUser = bean.queryByExample(user);
		System.out.println(foundUser);

	}
}
