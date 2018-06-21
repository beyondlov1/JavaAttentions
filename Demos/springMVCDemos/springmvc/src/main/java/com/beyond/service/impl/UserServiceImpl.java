package com.beyond.service.impl;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;
import com.beyond.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Override
	public User findUserById(String id) {
		User foundUser = userDao.selectById(id);
		return foundUser;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
