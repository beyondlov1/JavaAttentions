package com.beyond.demo1.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beyond.demo1.entity.Book;
import com.beyond.demo1.entity.CollectionTest;
import com.beyond.demo1.entity.Customer;

/*
 * ��ȡ�ļ������ַ�ʽ : ApplicationContext BeanFactory ClassPathX...
 * ��ö�������ַ�ʽ : ����(�������ļ���ֱ��дproperty)  ��̬����  ʵ������ [ ��׼���� ]
 * ����ע������ַ�ʽ: setter(�����ļ���property) 
 */
public class TestSpring {

	private ApplicationContext context;

	@Before
	public void before() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	// ����ע��: setter����(���������ļ���ֱ��д)
	@Test
	public void test1() {
		Book book = (Book) context.getBean("myBook");
		System.out.println(book.getId());
	}

	// ��̬Bean�������bean
	@Test
	public void test7() {
		Book book = (Book) context.getBean("myBookFactoryStatic");
		System.out.println(book);
	}

	// ʵ��Bean�������bean
	@Test
	public void test8() {
		Book book = (Book) context.getBean("myBook8");
		System.out.println(book);
	}

	// ��׼Bean�������bean
	@Test
	public void test9() {
		Book book = (Book) context.getBean("myBook9");
		System.out.println(book);
	}

	// ����ģʽ
	@Test
	public void test2() {
		Book book = (Book) context.getBean("myBook2");
		Book book2 = (Book) context.getBean("myBook2");
		System.out.println(book == book2);
	}

	// ����ģʽ
	@Test
	public void test3() {
		Book book = (Book) context.getBean("myBook3");
		Book book2 = (Book) context.getBean("myBook3");
		System.out.println(book == book2);
	}

	// ��������(���Դ˶δ���Ҫ���ϱ�before����ע����, ������ʼ������)
	@Test
	public void test4() {
		ClassPathXmlApplicationContext contextImpl = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("service");
		contextImpl.close();
	}

	// DI ����ע��
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

	// ����ע��ֵ: ���췽��
	@Test
	public void test10() {
		Book book = (Book) context.getBean("myBook10");
		System.out.println(book.getName());
	}

	// ����ע��ֵ: p�����ռ�ע��
	@Test
	public void test11() {
		Book book = (Book) context.getBean("myBook11");
		System.out.println(book.getName());
	}

	// ����ע��
	@Test
	public void test12() {
		CollectionTest collection = (CollectionTest) context.getBean("myCollectionTest");
		System.out.println(collection.getList());
	}

}
