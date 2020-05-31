package com.beyond.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.controller.BookAction;

public class TestSpring {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookAction ba = (BookAction) context.getBean("bookAction");
		ba.addBook();
	}
}
