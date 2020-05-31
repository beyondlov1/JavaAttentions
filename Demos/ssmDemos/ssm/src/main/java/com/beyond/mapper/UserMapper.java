package com.beyond.mapper;

import java.util.Set;

import com.beyond.entity.User;

public interface UserMapper {

	void addUser(User user);

	void deleteUser(String id);

	void updateUser(User user);

	User selectById(String id);

	Set<User> selectByExample(User user);

	Set<User> selectAll();
}
