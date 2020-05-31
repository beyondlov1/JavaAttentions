package com.beyond.servlet.reflect;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.entity.Category;
import com.beyond.exception.IllegalArgumentException;
import com.beyond.proxy.ServiceFactory;
import com.beyond.service.CategoryService;
import com.beyond.service.impl.CategoryServiceImpl;
import com.beyond.utils.BeanUtils;

@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {

	private CategoryService cs = (CategoryService) ServiceFactory.getInstance().getService(CategoryServiceImpl.class);

	public void addCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = BeanUtils.fillBean(request, Category.class);
		try {
			cs.saveCategory(category);
			response.sendRedirect(request.getContextPath() + "/CategoryServlet?op=showCategory");
		} catch (IllegalArgumentException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/add_category.jsp").forward(request, response);
		}
	}

	public void showCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = cs.findAll();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/show_category.jsp").forward(request, response);
	}

	public void removeCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		cs.removeCategory(id);
		response.sendRedirect(request.getContextPath() + "/CategoryServlet?op=showCategory");
	}

	public void initModifyCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Category category = cs.findCategoryById(id);
		request.setAttribute("category", category);
		request.getRequestDispatcher("/modify_category.jsp").forward(request, response);
	}

	public void modifyCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category category = BeanUtils.fillBean(request, Category.class);
		cs.modifyCategory(category);
		response.sendRedirect(request.getContextPath() + "/CategoryServlet?op=showCategory");
	}

	public void initAddCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/add_category.jsp").forward(request, response);
	}
}
