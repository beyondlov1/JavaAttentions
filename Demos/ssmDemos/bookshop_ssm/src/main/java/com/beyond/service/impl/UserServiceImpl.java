package com.beyond.service.impl;

import com.beyond.entity.User;
import com.beyond.mapper.UserMapper;
import com.beyond.service.UserService;

public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Override
	public void register(User user) {
		String username = user.getUsername();
		User foundUser = userMapper.selectUserByName(username);
		if (foundUser == null) {
			userMapper.add(user);
		} else {
			throw new RuntimeException("username repeat");
		}
	}

	@Override
	public User login(User user) {
		if (user.getUsername() == null || user.getUsername().length() == 0) {
			return null;
		}
		User foundUser = userMapper.selectByExample(user);
		return foundUser;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
