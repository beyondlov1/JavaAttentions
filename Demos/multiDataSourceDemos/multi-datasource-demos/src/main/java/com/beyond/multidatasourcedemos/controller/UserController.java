package com.beyond.multidatasourcedemos.controller;

import com.beyond.multidatasourcedemos.mapper.UserMapper;
import com.beyond.multidatasourcedemos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/showAllUser")
    public Object showAllUser(){
        return userService.findAll();
    }

    @RequestMapping("/showAllUser2")
    public Object showAllUser2(){
        return userService.findAll2();
    }
}
