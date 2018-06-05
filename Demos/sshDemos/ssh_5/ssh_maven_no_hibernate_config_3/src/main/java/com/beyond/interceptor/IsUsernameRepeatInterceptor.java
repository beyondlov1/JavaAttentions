package com.beyond.interceptor;

import org.apache.struts2.dispatcher.HttpParameters;

import com.beyond.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class IsUsernameRepeatInterceptor extends AbstractInterceptor {

	private UserServiceImpl userServiceImpl;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// 判断用户名是否存在
		HttpParameters parameters = invocation.getInvocationContext().getParameters();
		String username = parameters.get("user.username").getValue();
		if (userServiceImpl.isUsernameExist(username)) {
			ActionSupport action = (ActionSupport) invocation.getAction();
			action.addActionError("user is exsit");
			return Action.INPUT;
		}
		return invocation.invoke();
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

}
