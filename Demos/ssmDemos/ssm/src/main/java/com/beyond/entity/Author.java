package com.beyond.entity;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.Set;

public class Author {

	private String id;
	private String name;
	private Set<Book> books = new HashSet<>();

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

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
