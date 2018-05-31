package com.beyond.demo1.entity;

import org.springframework.beans.factory.FactoryBean;

public class BookFactoryStandard implements FactoryBean<Book> {

	@Override
	public Book getObject() throws Exception {

		return new Book();
	}

	@Override
	public Class<?> getObjectType() {
		return Book.class;
	}

}
