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

@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {

	public ShowUserServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = null;

		UserService us = new UserService();
		try {
			user = us.selectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(user);
		request.setAttribute("user", user);
		request.getRequestDispatcher("showUserById.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
