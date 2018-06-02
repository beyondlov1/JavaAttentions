package com.beyond.service.impl;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;
import com.beyond.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(User user) {
        userDao.addUser(user);
    }

    public void modifyUser(User user) {
        userDao.updateUser(user);
    }

    public void removeUser(User user) {
        userDao.deleteUser(user);
    }

    public User findById(String id) {
        return userDao.selectById(id);
    }

    public List<User> findAll() {
        return userDao.selectAll();
    }
}
