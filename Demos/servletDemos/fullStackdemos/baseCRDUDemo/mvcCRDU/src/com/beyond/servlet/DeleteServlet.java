package com.beyond.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.service.UserService;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

	public DeleteServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		UserService us = new UserService();
		try {
			us.deleteUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("ShowAllUser");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
