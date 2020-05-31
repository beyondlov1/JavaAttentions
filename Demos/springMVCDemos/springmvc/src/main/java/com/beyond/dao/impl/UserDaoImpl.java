package com.beyond.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	@Override
	public User selectById(String id) {

		String sql = "select * from user where id= ?";
		User foundUser = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}

		});

		return foundUser;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
