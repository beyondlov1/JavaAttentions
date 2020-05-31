package com.beyond.demo2.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("customerAnnotation")
public class CustomerAnnotation {
	private String id;
	private String name;

	@Autowired
	@Qualifier(value = "bookAnnotation")
	private BookAnnotaion bookAnnotation;

	public void init() {
		System.out.println("CustomerAnnotation is inited");
	}

	public void destroy() {
		System.out.println("CustomerAnnotation is destroyed");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BookAnnotaion getBookAnnotation() {
		return bookAnnotation;
	}

	public void setBookAnnotation(BookAnnotaion bookAnnotation) {
		this.bookAnnotation = bookAnnotation;
	}

}
