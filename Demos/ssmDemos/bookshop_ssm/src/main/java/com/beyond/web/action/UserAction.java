package com.beyond.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beyond.entity.User;
import com.beyond.service.UserService;
import com.beyond.utils.CookieUtils;
import com.beyond.validation.group.UserValidationGroup;

@Controller
@RequestMapping("/user")
public class UserAction {

	private UserService userService;

	@RequestMapping("/index")
	public String initIndex() {
		String loginUserName = CookieUtils.getCookie("loginUserName");
		if (loginUserName != null && loginUserName != "") {
			return "index";
		} else {
			return "forward:/user/initLogin.action";
		}

	}

	@RequestMapping("/initRegister")
	public String initRegister(Model model) {
		model.addAttribute("user", new User());
		return "pages/register";
	}

	@RequestMapping("/register")
	public String register(@Validated(value = UserValidationGroup.class) User user, Errors errors, Model model) {

		// validate
		if (errors.hasErrors()) {
			return "pages/register";
		}

		// register
		try {
			userService.register(user);
			model.addAttribute("user", user);
			return "redirect:/pages/login.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "pages/register";
		}

	}

	@RequestMapping("/initLogin")
	public String initLogin(Model model) {
		model.addAttribute("user", new User());
		return "redirect:/pages/login.jsp";
	}

	@RequestMapping("/login")
	public String login(User user, Model model) {

		User loginUser = userService.login(user);
		if (loginUser != null) {
			CookieUtils.setCookie("loginUserName", loginUser.getUsername());
			CookieUtils.setCookie("loginUserId", loginUser.getId());
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("msg", "failed");
			return "pages/login";
		}
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		CookieUtils.deleteCookie("loginUserId");
		CookieUtils.deleteCookie("loginUserName");
		return "pages/login";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
