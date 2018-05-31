package com.beyond.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.entity.User;
import com.beyond.service.UserService;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {

	public InsertServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setPassword(password);

		UserService us = new UserService();
		try {
			us.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("ShowAllUser");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
