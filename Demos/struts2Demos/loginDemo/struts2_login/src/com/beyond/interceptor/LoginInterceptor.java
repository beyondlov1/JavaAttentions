package com.beyond.interceptor;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.beyond.entity.Userinfo;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {

		Userinfo user = (Userinfo) ServletActionContext.getRequest().getSession().getAttribute("user");
		ActionSupport action = (ActionSupport) ai.getAction();

		if (user == null) {
			// 没有登录 -- 判断有无自动登录
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			boolean isLogined = false;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("loginedUsername")) {
						isLogined = true;
						break;
					}
				}
			}
			if (isLogined) {
				return ai.invoke();
			} else {
				action.addActionError("还未登录");
				return Action.INPUT;
			}

		} else {
			// 登陆了
			return ai.invoke();
		}

	}

}
