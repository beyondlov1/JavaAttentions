package com.beyond.action;

import com.beyond.entity.User;
import com.beyond.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class UserAction extends ActionSupport {

	private User user;

	private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String findAllUser(){
        List<User> list = userService.findAll();
        ActionContext.getContext().put("list", list);
        System.out.println(list.get(0).getPassword());
        return SUCCESS;
	}

	public String login() {

		return SUCCESS;
	}

	public String initLogin() {

		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
