package com.beyond.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beyond.service.BookService;

/*
 * use spring aop to implement proxy(example: transaction)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {

	@Resource(name = "bookServiceFactory")
	private BookService bookService;

	@Test
	public void test() {
		bookService.addBook();
	}

	@Test
	public void test2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		BookService bookService2 = (BookService) context.getBean("bookServiceFactory");
		bookService2.addBook();
	}

}
