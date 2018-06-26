package com.beyond.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class Order implements Serializable {

	private Book book;
	private User borrower;
	private Timestamp createTime;
	private Book exchangeBook;

	private Integer ownerPermissionStatus;
	private Integer ownerConfirmStatus;
	private Integer borrowerConfirmStatus;

	public Book getExchangeBook() {
		return exchangeBook;
	}

	public void setExchangeBook(Book exchangeBook) {
		this.exchangeBook = exchangeBook;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getOwnerPermissionStatus() {
		return ownerPermissionStatus;
	}

	public void setOwnerPermissionStatus(Integer ownerPermissionStatus) {
		this.ownerPermissionStatus = ownerPermissionStatus;
	}

	public Integer getOwnerConfirmStatus() {
		return ownerConfirmStatus;
	}

	public void setOwnerConfirmStatus(Integer ownerConfirmStatus) {
		this.ownerConfirmStatus = ownerConfirmStatus;
	}

	public Integer getBorrowerConfirmStatus() {
		return borrowerConfirmStatus;
	}

	public void setBorrowerConfirmStatus(Integer borrowerConfirmStatus) {
		this.borrowerConfirmStatus = borrowerConfirmStatus;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
