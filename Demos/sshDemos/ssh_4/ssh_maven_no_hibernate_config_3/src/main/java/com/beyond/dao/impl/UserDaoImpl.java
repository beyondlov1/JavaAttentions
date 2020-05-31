package com.beyond.dao.impl;

import java.util.List;

import com.beyond.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> {

	public User selectById(String id) {
		return super.selectById(User.class, id);
	}

	public List<User> selectAll() {
		return super.selectAll(User.class);
	}

	@Override
	public User selectByExample(User user) {
		return super.selectByExample(user);
	}
}
