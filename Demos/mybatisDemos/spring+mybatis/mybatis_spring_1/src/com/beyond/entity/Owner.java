package com.beyond.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Owner entity. @author MyEclipse Persistence Tools
 */

public class Owner implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String password;
	private Set books = new HashSet(0);

	// Constructors

	/** default constructor */
	public Owner() {
	}

	/** full constructor */
	public Owner(String username, String password, Set books) {
		this.username = username;
		this.password = password;
		this.books = books;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getBooks() {
		return this.books;
	}

	public void setBooks(Set books) {
		this.books = books;
	}

}