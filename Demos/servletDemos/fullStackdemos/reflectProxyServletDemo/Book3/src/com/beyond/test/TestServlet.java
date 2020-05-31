package com.beyond.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Book;
import com.beyond.proxy.DAOFactory;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	public TestServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		BaseDao baseDao = DAOFactory.getInstance().getBaseDao();
		Book book = baseDao.selectBean(Book.class, "1");
		System.out.println(book.getId() + " " + book.getCategory().getName());

		out.write(book.getCategory().getName());

		/* read form infromation */

		/* service */

		/*
		 * redirect+send message(Request,Response,Session,ServletContext,Cookie)
		 */

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
