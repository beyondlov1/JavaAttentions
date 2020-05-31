package test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;

public class TestJDBC {

	@Test
	public void testJDBC() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		User foundUser = userDao.selectById("4391e76d6e5111e8ab69107b447deebe");
		System.out.println(foundUser.getUsername());
	}
}
