package com.beyond.service;

import com.beyond.entity.User;

import java.util.List;


public interface UserService {

    void saveUser(User user);
    void modifyUser(User user);
    void removeUser( User user);
    User findById(String id);
    List<User> findAll();
}
