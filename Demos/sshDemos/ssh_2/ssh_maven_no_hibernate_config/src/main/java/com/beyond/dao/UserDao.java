package com.beyond.dao;

import java.util.List;

import com.beyond.entity.User;

public interface UserDao {
	void addUser(User user);

	void deleteUser(User user);

	void updateUser(User user);

	User selectById(String id);

	User selectByExample(User user);

	List<User> selectAll();
}
