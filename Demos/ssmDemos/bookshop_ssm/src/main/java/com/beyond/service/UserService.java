package com.beyond.service;

import com.beyond.entity.User;

public interface UserService {

	void register(User user);

	User login(User user);

}
