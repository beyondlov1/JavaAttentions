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
			// û�е�¼ -- �ж������Զ���¼
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
				action.addActionError("��δ��¼");
				return Action.INPUT;
			}

		} else {
			// ��½��
			return ai.invoke();
		}

	}

}
