package com.beyond.dao;

import com.beyond.entity.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
    User selectById(String id);
    List<User> selectAll();
}
