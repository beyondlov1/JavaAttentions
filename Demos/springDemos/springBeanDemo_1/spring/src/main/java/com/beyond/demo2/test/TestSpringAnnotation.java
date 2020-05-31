package com.beyond.demo2.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.demo2.entity.CustomerAnnotation;

/*
 * annotation
 */
public class TestSpringAnnotation {

	private ApplicationContext context;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	// ≤‚ ‘annotation
	@Test
	public void test1() {
		CustomerAnnotation customerAnnotation = (CustomerAnnotation) context.getBean("customerAnnotation");
		System.out.println(customerAnnotation);
		System.out.println(customerAnnotation.getBookAnnotation());
	}

}
