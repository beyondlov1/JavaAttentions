package com.beyond.mapper;

import com.beyond.entity.User;

public interface UserMapper {

	User queryByExample(User user);
}
