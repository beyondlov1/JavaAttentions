package com.beyond.entity;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

	// Fields

	private String id;
	private Author author;
	private Category category;
	private Owner owner;
	private String bookName;
	private Double price;
	private Integer remainCount;

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** full constructor */
	public Book(Author author, Category category, Owner owner, String bookName, Double price, Integer remainCount) {
		this.author = author;
		this.category = category;
		this.owner = owner;
		this.bookName = bookName;
		this.price = price;
		this.remainCount = remainCount;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRemainCount() {
		return this.remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}

}