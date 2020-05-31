package com.beyond.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.beyond.entity.Page;
import com.beyond.entity.User;
import com.beyond.service.impl.UserServiceImpl;
import com.beyond.utils.UserUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user;

	private String repassword;

	private Integer currentPageNum;

	private UserServiceImpl userServiceImpl;

	// signup
	public String signup() {
		userServiceImpl.save(user);
		return SUCCESS;
	}

	// login
	public String login() {
		User foundUser = userServiceImpl.find(user);
		if (foundUser == null) {
			this.addActionError("username or password is wrong");
			return INPUT;
		}
		UserUtils.setLoginUser(foundUser);
		return SUCCESS;
	}

	// 查询所有用户信息
	public String findAllUser() {
		List<User> allUserList = userServiceImpl.findAll();
		ActionContext.getContext().put("allUserList", allUserList);
		return SUCCESS;
	}

	// 查询所有用户信息 分页
	public String findAllUserPage() {
		Page<User> allUserPage = userServiceImpl.findAllByPage(currentPageNum);
		ActionContext.getContext().put("allUserPage", allUserPage);
		return SUCCESS;
	}

	// to login
	public String initLogin() {
		return SUCCESS;
	}

	// to signup
	public String initSignup() {
		return SUCCESS;
	}

	// ajax
	public String ajaxVertifyUser() {

		String message = "";

		String username = user.getUsername();

		Boolean isUserExist = userServiceImpl.isUsernameExist(username);

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

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCurrentPageNum(Integer currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

}
