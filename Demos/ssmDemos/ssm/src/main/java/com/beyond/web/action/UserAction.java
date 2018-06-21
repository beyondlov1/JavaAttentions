package com.beyond.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beyond.entity.User;
import com.beyond.service.UserService;

@Controller
public class UserAction {

	private UserService userService;

	@RequestMapping("/register")
	public String register(User user, Model model) {

		userService.register(user);

		String status = user.getUsername();

		model.addAttribute("status", status);

		return "index";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
