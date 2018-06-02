package com.beyond.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.beyond.entity.Userinfo;
import com.beyond.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	public static final Logger log = LogManager.getLogger();

	private Userinfo user = new Userinfo();

	private String repassword;

	public String login() {
		UserService us = new UserService();
		log.info(user.getUsername());
		try {
			Userinfo sqlUser = us.login(user);
			if (sqlUser == null) {
				this.addActionError("用户名或密码错误!");
				return INPUT;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.put("user", user);

		// 自动登录 -- 登陆成功加入cookie
		Cookie cookie = new Cookie("loginedUsername", user.getUsername());
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookie.setPath("/struts2_login");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);

		return SUCCESS;
	}

	public String signup() {
		log.info("ok");
		UserService us = new UserService();
		log.info(user.getUsername());

		try {

			if (us.findByUsername(user) != null) {
				this.addActionError("用户已存在!");
				return INPUT;
			}
			us.signup(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.put("loginUsername", user.getUsername());

		return SUCCESS;
	}

	public String isUserExist() throws SQLException, IOException {
		UserService us = new UserService();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");

		log.info(repassword);

		if (us.findByUsername(user) != null) {
			response.getWriter().write("用户已存在!");
		} else {
			response.getWriter().write("用户名可用!");
		}
		return NONE;
	}

	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
}
