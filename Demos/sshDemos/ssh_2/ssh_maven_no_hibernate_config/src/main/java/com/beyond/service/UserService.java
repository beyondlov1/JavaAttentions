package com.beyond.service;

import java.util.List;

import com.beyond.entity.User;

public interface UserService {

	void saveUser(User user);

	void modifyUser(User user);

	void removeUser(User user);

	User findUser(String id);

	User findUser(User user);

	Boolean isUserExist(User user);

	List<User> findAll();
}
