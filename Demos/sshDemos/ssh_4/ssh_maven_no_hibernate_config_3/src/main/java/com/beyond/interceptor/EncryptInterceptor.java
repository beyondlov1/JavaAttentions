package com.beyond.interceptor;

import com.beyond.entity.User;
import com.beyond.utils.SecureUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EncryptInterceptor extends AbstractInterceptor {

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ModelDriven<User> modelDriven = (ModelDriven<User>) invocation.getAction();

		// √‹¬Îº”√‹
		User user = (User) modelDriven.getModel();
		String encryptString = SecureUtils.getEncryptString(user.getPassword());
		user.setPassword(encryptString);
		return invocation.invoke();
	}

}
