package com.beyond.demo1.entity;

public class Book {
	private String id;
	private String name;
	private Double price;

	public Book() {
	}

	public Book(String id, String name, Double price) {

		this.id = id;
		this.name = name;
		this.price = price;
		System.out.println("i am args constructor");
	}

	public void init() {
		System.out.println("book is inited");
	}

	public void destroy() {
		System.out.println("book is destroyed");
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
