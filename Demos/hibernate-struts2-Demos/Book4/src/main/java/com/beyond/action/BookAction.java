package com.beyond.action;

import java.util.List;

import com.beyond.entity.Author;
import com.beyond.entity.Book;
import com.beyond.entity.Owner;
import com.beyond.service.Service;
import com.beyond.service.impl.ServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {

	@SuppressWarnings("rawtypes")
	Service bs = new ServiceImpl();

	private Book book;

	private Owner owner;

	private Author author;

	@SuppressWarnings("unchecked")
	public String showAllBooks() {
		List<Book> list = bs.findAll(Book.class);

		ActionContext.getContext().put("list", list);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String addBook() {

		if (!author.getName().equals("")) {
			author.getBooks().add(book);
			bs.add(author);
			book.setAuthor(author);
		} else {
			book.setAuthor(null);
		}
		if (!owner.getName().equals("")) {
			owner.getBooks().add(book);
			bs.add(owner);
			book.setOwner(owner);
		} else {
			book.setOwner(null);
		}
		bs.add(book);

		return "SHOW";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
