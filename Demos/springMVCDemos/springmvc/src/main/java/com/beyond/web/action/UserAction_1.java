package com.beyond.web.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyond.entity.User;

@Controller
@RequestMapping("/user")
public class UserAction_1 {

	@RequestMapping(value = "/hello.action")
	public @ResponseBody List<User> hello(Model model, User user) {
		List<User> list = new ArrayList<>();
		User user1 = new User();
		user1.setUsername("aaa");
		User user2 = new User();
		user2.setUsername("bbb");
		User user3 = new User();
		user3.setUsername("ccc");
		User user4 = new User();
		user4.setUsername("ddd");
		list.add(user1);
		list.add(user2);
		list.add(user3);
		list.add(user4);
		return list;
	}
}
