package com.beyond.dao;

import com.beyond.entity.User;

public interface UserDao {

	User selectById(String id);
}
