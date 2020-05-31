package com.beyond.demo1.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.demo1.entity.Book;
import com.beyond.demo1.entity.CollectionTest;
import com.beyond.demo1.entity.Customer;

/*
 * 读取文件的三种方式 : ApplicationContext BeanFactory ClassPathX...
 * 获得对象的三种方式 : 构造(在配置文件中直接写property)  静态工厂  实例工厂 [ 标准工厂 ]
 * 对象注入的三种方式: setter(配置文件中property) 
 */
public class TestSpring {

	private ApplicationContext context;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	// 对象注入: setter方法(就是配置文件上直接写)
	@Test
	public void test1() {
		Book book = (Book) context.getBean("myBook");
		System.out.println(book.getId());
	}

	// 静态Bean工厂获得bean
	@Test
	public void test7() {
		Book book = (Book) context.getBean("myBookFactoryStatic");
		System.out.println(book);
	}

	// 实例Bean工厂获得bean
	@Test
	public void test8() {
		Book book = (Book) context.getBean("myBook8");
		System.out.println(book);
	}

	// 标准Bean工厂获得bean
	@Test
	public void test9() {
		Book book = (Book) context.getBean("myBook9");
		System.out.println(book);
	}

	// 单例模式
	@Test
	public void test2() {
		Book book = (Book) context.getBean("myBook2");
		Book book2 = (Book) context.getBean("myBook2");
		System.out.println(book == book2);
	}

	// 多例模式
	@Test
	public void test3() {
		Book book = (Book) context.getBean("myBook3");
		Book book2 = (Book) context.getBean("myBook3");
		System.out.println(book == book2);
	}

	// 生命周期(测试此段代码要把上边before方法注销掉, 否则会初始化两次)
	@Test
	public void test4() {
		ClassPathXmlApplicationContext contextImpl = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("service");
		contextImpl.close();
	}

	// DI 依赖注入
	@Test
	public void test5() {
		Customer customer = (Customer) context.getBean("myCustomer5");
		System.out.println(customer.getBook().getName());
	}

	// BeanPostProcessor
	@Test
	public void test6() {
		Book book2 = (Book) context.getBean("myBook3");
	}

	// 对象注入值: 构造方法
	@Test
	public void test10() {
		Book book = (Book) context.getBean("myBook10");
		System.out.println(book.getName());
	}

	// 对象注入值: p命名空间注入
	@Test
	public void test11() {
		Book book = (Book) context.getBean("myBook11");
		System.out.println(book.getName());
	}

	// 集合注入
	@Test
	public void test12() {
		CollectionTest collection = (CollectionTest) context.getBean("myCollectionTest");
		System.out.println(collection.getList());
	}

}
