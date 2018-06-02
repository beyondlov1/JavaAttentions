package com.beyond.service.impl;

import java.util.List;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;
import com.beyond.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void saveUser(User user) {
		userDao.addUser(user);
	}

	public void modifyUser(User user) {
		userDao.updateUser(user);
	}

	public void removeUser(User user) {
		userDao.deleteUser(user);
	}

	public User findUser(String id) {
		return userDao.selectById(id);
	}

	public List<User> findAll() {
		return userDao.selectAll();
	}

	@Override
	public User findUser(User user) {
		return userDao.selectByExample(user);
	}

	@Override
	public Boolean isUserExist(User user) {
		User foundUser = userDao.selectByExample(user);
		if (foundUser == null) {
			return false;
		}
		return true;
	}
}
