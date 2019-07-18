package com.beyond.demo.mapper;

import com.beyond.demo.bean.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAllUser();
}
