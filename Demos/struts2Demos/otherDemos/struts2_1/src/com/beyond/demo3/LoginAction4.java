package com.beyond.demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * ��װ����(model����)
 */
public class LoginAction4 extends ActionSupport implements ModelDriven<User> {

	private static final Logger log = LogManager.getLogger();

	private User user = new User();

	public String execute() {
		log.info(user.getUsername());
		log.info(user.getPassword());
		return SUCCESS;
	}

	@Override
	public User getModel() {
		return user;
	}

}
