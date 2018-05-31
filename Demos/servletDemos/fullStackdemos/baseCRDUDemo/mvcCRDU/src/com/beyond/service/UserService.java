package com.beyond.service;

import java.sql.SQLException;
import java.util.List;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;

public class UserService {
	UserDao userDao = new UserDao();

	public void addUser(User user) throws SQLException {

		userDao.addUser(user);
	}

	public void deleteUserById(String id) throws SQLException {
		userDao.deleteUserById(id);
	}

	public void updateUserById(String id, User user) throws SQLException {

		userDao.updateUserById(id, user);
	}

	public User selectById(String id) throws SQLException {

		return userDao.selectById(id);
	}

	public List<User> selectAll() throws SQLException {

		return userDao.selectAll();
	}

}
