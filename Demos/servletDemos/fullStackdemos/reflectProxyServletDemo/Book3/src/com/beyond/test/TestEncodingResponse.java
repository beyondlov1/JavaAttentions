package com.beyond.test;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestEncodingResponse")
public class TestEncodingResponse extends HttpServlet {

	public TestEncodingResponse() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("GBK");
		// response.setContentType("text/html");

		InputStream is = TestEncodingResponse.class.getResourceAsStream("/Test.txt");
		byte[] bytes = new byte[is.available()];
		is.read(bytes);

		String s = "utfffffa我们";
		String s2 = new String("utfffffff");
		// OutputStream os = response.getOutputStream();
		// os.write(new String(bytes, "GBK").getBytes("UTF-8"));

		// os.flush();
		response.getWriter().println(new String(s.getBytes("UTF-8"), "UTF-8"));

		System.out.println(request);
		// request.getRequestDispatcher("/index.jsp").forward(request,
		// response);
		response.sendRedirect("index.jsp");

		System.out.println(request.getRequestURI());
		// out.println(bytes);

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
