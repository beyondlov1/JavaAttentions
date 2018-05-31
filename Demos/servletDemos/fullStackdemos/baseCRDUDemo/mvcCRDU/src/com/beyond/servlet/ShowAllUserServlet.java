package com.beyond.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.entity.User;
import com.beyond.service.UserService;

@WebServlet("/ShowAllUser")
public class ShowAllUserServlet extends HttpServlet {

	public ShowAllUserServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = new ArrayList<>();
		UserService us = new UserService();
		try {
			list = us.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("user", list);
		request.getRequestDispatcher("showUser.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
