package com.beyond.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.beyond.entity.User;
import com.beyond.service.BaseService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class UserAction2 extends ActionSupport implements ModelDriven<User> {

	private User user;

	private String repassword;

	@Autowired
	private BaseService<User> baseService;

	/*
	 * public String findAllUser() { List<User> list = baseService.findAll();
	 * ActionContext.getContext().put("list", list); return SUCCESS; }
	 */
	// to signup
	public String initSignup() {
		return SUCCESS;
	}

	// signup
	public String signup() {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		baseService.save(user);
		return SUCCESS;
	}

	// to login
	public String initLogin() {
		return SUCCESS;
	}

	// login
	public String login() {
		User foundUser = baseService.find(user);
		if (foundUser == null) {
			this.addActionError("username or password is wrong");
			return INPUT;
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("loginUser", user);
		return SUCCESS;
	}

	// ajax
	public String ajaxVertifyUser() {

		String message = "";

		String username = user.getUsername();
		System.out.println(username);
		Boolean isUserExist = baseService.isExist(user);

		if (!isUserExist) {
			if (username.length() >= 6 && username.length() < 12) {
				message = "username avaliable";
			} else {
				message = "username length must be 6-12";
			}
		} else {
			message = "username not avaliable";
		}

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(message);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return NONE;
	}

	/*
	 * 手动验证 public void validate() { this.addFieldError("user.username",
	 * "username error"); }
	 * 
	 * public void validateSignup(){ this.addFieldError("user.username",
	 * "username error"); } }
	 */

	public void setBaseService(BaseService<User> baseService) {
		this.baseService = baseService;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	@Override
	public User getModel() {
		if (user == null) {
			user = new User();
		}
		return user;
	}

	public User getUser() {
		return user;
	}

}
