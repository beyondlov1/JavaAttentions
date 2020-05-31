package com.beyond.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.beyond.entity.User;
import com.beyond.service.UserService;

public class UserAction implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "i am message");

		WebApplicationContext context = (WebApplicationContext) request
				.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		User foundUser = userService.findUserById("4391e76d6e5111e8ab69107b447deebe");

		modelAndView.addObject("foundUser", foundUser);
		modelAndView.setViewName("index");
		return modelAndView;
	}

}
