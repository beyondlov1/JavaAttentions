package com.beyond.entity;

public class Customer {
	private String id;
	private String name;

	private Book book;

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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
