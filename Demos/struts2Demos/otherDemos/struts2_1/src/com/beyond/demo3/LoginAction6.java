package com.beyond.demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 校验数据(手动校验)
 */
public class LoginAction6 extends ActionSupport {

	private static final Logger log = LogManager.getLogger();

	private String username;
	private String password;

	public String execute() {
		log.info(username);
		log.info(password);
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void validate() {
		if ("".equals(username) || username == null) {
			this.addFieldError("username", "用户名不能为空...(手动配置的)");
		}
	}

}
