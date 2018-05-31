package com.beyond.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.entity.User;
import com.beyond.service.UserService;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

	public UpdateServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);

		UserService us = new UserService();
		try {
			us.updateUserById(id, user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("ShowAllUser");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
