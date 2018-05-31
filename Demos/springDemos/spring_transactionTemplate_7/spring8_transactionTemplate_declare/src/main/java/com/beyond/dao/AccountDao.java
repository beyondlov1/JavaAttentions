package com.beyond.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.beyond.entity.Account;

public class AccountDao extends JdbcDaoSupport {

	// private JdbcTemplate jdbcTemplate;

	public void updateMoney(Account a) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		jdbcTemplate.update("update spring_account set money=? where id=?", a.getMoney(), a.getId());
	}

	// public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	// this.jdbcTemplate = jdbcTemplate;
	// }

}
