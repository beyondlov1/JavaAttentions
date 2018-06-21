package com.beyond.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Author entity. @author MyEclipse Persistence Tools
 */

public class Author implements java.io.Serializable {

	// Fields

	private String id;
	private String authorName;
	private Set books = new HashSet(0);

	// Constructors

	/** default constructor */
	public Author() {
	}

	/** full constructor */
	public Author(String authorName, Set books) {
		this.authorName = authorName;
		this.books = books;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Set getBooks() {
		return this.books;
	}

	public void setBooks(Set books) {
		this.books = books;
	}

}