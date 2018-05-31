package com.beyond.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beyond.service.BookService;

/*
 * aspectJ aop
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring3 {

	@Resource(name = "bookService")
	private BookService bookService;

	@Test
	public void test() {
		bookService.addBook();
	}

}
