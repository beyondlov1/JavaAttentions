package com.beyond.demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 封装数据(属性驱动  1)
 */
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -1262302942677858476L;

	private static final Logger log = LogManager.getLogger();

	private String username;
	private String password;

	public String execute() {
		log.info(username);
		log.info(password);
		return SUCCESS;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
