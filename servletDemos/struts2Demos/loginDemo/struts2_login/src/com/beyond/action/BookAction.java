package com.beyond.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport {

	public static final Logger log = LogManager.getLogger();

	public String findAllBook() {

		System.out.println("≤È’“Õº È/....");
		return NONE;
	}

	public String deleteBook() {
		System.out.println("…æ≥˝Õº È/....");

		return NONE;
	}
}
