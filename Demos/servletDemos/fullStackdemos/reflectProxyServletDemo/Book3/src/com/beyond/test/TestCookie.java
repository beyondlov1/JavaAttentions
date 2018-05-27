package com.beyond.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestCookie")
public class TestCookie extends HttpServlet {

	public TestCookie() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		/* read form infromation */
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			System.out.println(name);
		}
		/* service */

		/*
		 * redirect+send message(Request,Response,Session,ServletContext,Cookie)
		 */

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
