package com.beyond.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beyond.controller.BookAction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {

	@Resource(name = "bookAction")
	private BookAction bookAction;

	@Test
	public void test2() {
		bookAction.addBook();
	}
}
