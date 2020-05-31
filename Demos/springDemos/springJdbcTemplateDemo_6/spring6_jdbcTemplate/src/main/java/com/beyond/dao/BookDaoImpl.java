package com.beyond.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {

	private JdbcTemplate jdbcTemplate;

	public void addBook() {

		jdbcTemplate.update("insert into springjdbctest values(?)", "test");

		System.out.println("Dao addBook");
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
