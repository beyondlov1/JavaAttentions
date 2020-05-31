package com.beyond.demo5;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class ValueStackDemo extends ActionSupport {

	private User user = new User("username2", "password2");

	private User user1;

	public String execute() {
		ValueStack vs = ActionContext.getContext().getValueStack();
		User user = new User();
		user.setUsername("username1");
		user.setPassword("password1");
		// user1 = new User();
		// user1.setUsername("username3");
		// user1.setPassword("password3");
		vs.push(user);
		// vs.push(user1);
		ServletActionContext.getRequest().setAttribute("msg", "message");
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

}
