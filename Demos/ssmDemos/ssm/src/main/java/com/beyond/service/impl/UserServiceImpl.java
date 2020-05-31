package com.beyond.service.impl;

import com.beyond.entity.User;
import com.beyond.mapper.UserMapper;
import com.beyond.service.UserService;

public class UserServiceImpl implements UserService {

	private UserMapper userMapper;

	@Override
	public void register(User user) {
		userMapper.addUser(user);
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

}
