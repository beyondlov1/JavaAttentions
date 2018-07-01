package com.beyond.mapper;

import com.beyond.entity.User;

public interface UserMapper extends BaseMapper<User> {
    User selectUserByName(String username);
}
