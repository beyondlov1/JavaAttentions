package com.beyond.servlet.reflect;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.entity.Book;
import com.beyond.proxy.ServiceFactory;
import com.beyond.service.BookService;
import com.beyond.service.CartService;
import com.beyond.service.impl.BookServiceImpl;
import com.beyond.service.impl.CartServiceImpl;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

	ServiceFactory sf = ServiceFactory.getInstance();

	public int putInCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CartService cs = (CartService) sf.getService(CartServiceImpl.class);

		String currentPageNum = request.getParameter("currentPageNum");

		System.out.println("CartServlet..........");
		String id = request.getParameter("id");
		Book book = new Book();
		book.setId(id);
		cs.putInCart(book);

		System.out.println("currentPageNum=====" + currentPageNum);
		response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook&currentPageNum=" + currentPageNum);

		return 1;
	}

	public int showAllCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CartService cs = (CartService) sf.getService(CartServiceImpl.class);
		BookService bs = (BookService) sf.getService(BookServiceImpl.class);
		System.out.println("showAllCart..........");

		Map<String, String> allCart = cs.findAllCart();
		String text = "";

		Iterator<String> it = allCart.keySet().iterator();
		text += "<table border='1' style='width:100%'>";
		while (it.hasNext()) {
			String key = it.next();
			text += "<tr>";
			text += "<td>";
			text += bs.findBookById(key).getName();
			text += "</td>";
			text += "<td>";
			text += allCart.get(key);
			text += "</td>";
			text += "</tr>";
		}
		text += "</table>";
		PrintWriter out = response.getWriter();
		out.write(text);
		out.flush();
		out.close();

		return 1;
	}

}
