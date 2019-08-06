package com.beyond.multidatasourcedemos.mapper;

import com.beyond.multidatasourcedemos.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
    List<User> selectAllH2();
}
