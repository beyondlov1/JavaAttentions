package com.beyond.demo2.entity;

import org.springframework.stereotype.Component;

@Component("bookAnnotation")
public class BookAnnotaion {
	private String id;
	private String name;
	private Double price;

	public void init() {
		System.out.println("bookAnnotation is inited");
	}

	public void destroy() {
		System.out.println("bookAnnotation is destroyed");
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
