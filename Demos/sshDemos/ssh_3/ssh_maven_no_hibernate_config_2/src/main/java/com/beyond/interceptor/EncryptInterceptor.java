package com.beyond.interceptor;

import com.beyond.entity.User;
import com.beyond.utils.SecureUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EncryptInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext context = invocation.getInvocationContext().getContext();
		ModelDriven modelDriven = (ModelDriven) invocation.getAction();

		User user = (User) modelDriven.getModel();

		// √‹¬Îº”√‹
		String encryptString = SecureUtils.getEncryptString(user.getPassword());
		user.setPassword(encryptString);
		System.out.println("userPassword  " + user.getPassword());

		return invocation.invoke();
	}

}
