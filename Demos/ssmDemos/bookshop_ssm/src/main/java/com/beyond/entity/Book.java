package com.beyond.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class Book implements Serializable {

	private String id;
	private String name;
	private Double price;
	private Integer count;
	private String bookUri;
	private String coverUri;

	private Author author;
	private User owner;
	private User borrower;
	private List<Order> orders = new ArrayList<>();

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		if (borrower == null) {
			this.borrower = owner;
		}
		this.borrower = borrower;
	}

	public String getBookUri() {
		return bookUri;
	}

	public void setBookUri(String bookUri) {
		this.bookUri = bookUri;
	}

	public String getCoverUri() {
		return coverUri;
	}

	public void setCoverUri(String coverUri) {
		this.coverUri = coverUri;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
