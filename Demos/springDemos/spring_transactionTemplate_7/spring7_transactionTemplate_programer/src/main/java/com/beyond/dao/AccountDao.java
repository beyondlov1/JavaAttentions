package com.beyond.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.beyond.entity.Account;

public class AccountDao {

	private JdbcTemplate jdbcTemplate;

	public void updateMoney(Account a) {
		jdbcTemplate.update("update spring_account set money=? where id=?", a.getMoney(), a.getId());
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
