package com.beyond.utils;

import java.util.Map;

import com.beyond.entity.User;
import com.beyond.f.F;
import com.opensymphony.xwork2.ActionContext;

public class UserUtils {

	public static User getLoginUser() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get(F.LOGIN_USER);
		return user;
	}

	public static void setLoginUser(User user) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(F.LOGIN_USER, user);
	}
}
