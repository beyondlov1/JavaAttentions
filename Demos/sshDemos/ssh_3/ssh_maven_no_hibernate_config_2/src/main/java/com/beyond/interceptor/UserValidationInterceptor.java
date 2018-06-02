package com.beyond.interceptor;

import org.apache.struts2.dispatcher.HttpParameters;

import com.beyond.entity.User;
import com.beyond.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserValidationInterceptor extends AbstractInterceptor {

	private UserService userService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		HttpParameters parameters = invocation.getInvocationContext().getParameters();
		String username = parameters.get("user.username").getValue();
		User user = new User();
		user.setUsername(username);
		if (userService.isUserExist(user)) {
			ActionSupport action = (ActionSupport) invocation.getAction();
			action.addActionError("user is exsit");
			return Action.INPUT;
		}

		return invocation.invoke();

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
