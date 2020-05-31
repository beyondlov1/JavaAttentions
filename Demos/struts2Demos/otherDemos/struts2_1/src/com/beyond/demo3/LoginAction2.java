package com.beyond.demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 封装数据(属性驱动  2)
 */
public class LoginAction2 extends ActionSupport {

	private static final Logger log = LogManager.getLogger();

	private User user;

	public String execute() {
		log.info(user.getUsername());
		log.info(user.getPassword());
		return SUCCESS;
	}

	public User getUser() {
		log.info("getUser");
		return user;
	}

	public void setUser(User user) {
		log.info("setUser");
		this.user = user;
	}

}
