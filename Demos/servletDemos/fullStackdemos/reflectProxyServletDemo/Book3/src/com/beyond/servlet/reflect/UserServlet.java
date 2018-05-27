package com.beyond.servlet.reflect;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.json.JSONStringer;

import com.beyond.entity.User;
import com.beyond.exception.NoLoginException;
import com.beyond.exception.NotFoundUserException;
import com.beyond.exception.NullPasswordException;
import com.beyond.exception.NullUsernameException;
import com.beyond.exception.UsernameRepeatException;
import com.beyond.proxy.ServiceFactory;
import com.beyond.service.UserService;
import com.beyond.service.impl.UserServiceImpl;
import com.beyond.utils.BeanUtils;
import com.beyond.utils.CookieUtils;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {

	private UserService us = (UserService) ServiceFactory.getInstance().getService(UserServiceImpl.class);

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// ��װ����
			User user = BeanUtils.fillBean(request, User.class);
			// ��¼
			us.vertifyUser(user, false);

			user = us.findUserByName(user.getUsername());// д��������user(��һ������)

			if ("autoLogin".equals(request.getParameter("autoLogin"))) {// autoLoginѡ��:
																		// ���cookie
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("username", user.getUsername());
				jsonObject.put("password", user.getPassword());
				Cookie cookie = new Cookie("userinfo", JSONStringer.valueToString(jsonObject));
				System.out.println(JSONStringer.valueToString(jsonObject));
				cookie.setMaxAge(60 * 60 * 24 * 30);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			} else {// autoLoginδ��ѡ��: ɾ��cookie�е�userinfo
				Cookie cookie = CookieUtils.getCookie("userinfo");
				if (cookie != null) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}

			// ת�� ������
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/BookServlet?op=showBook");
		} catch (NoLoginException | NotFoundUserException | NullPasswordException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void signup(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// ��װ����
		User user = BeanUtils.fillBean(request, User.class);
		try {
			// ҵ���߼�
			us.saveUser(user);
			// �ַ�
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (NullUsernameException | UsernameRepeatException | NullPasswordException e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
	}

	public void initLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	public boolean isUserExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserService us = (UserService) ServiceFactory.getInstance().getService(UserServiceImpl.class);
		String username = request.getParameter("username");
		User sqlUser = null;
		sqlUser = us.findUserByName(username.trim());
		if (sqlUser != null) {
			response.getWriter().write("�û��Ѵ���");
			return true;
		} else {
			response.getWriter().write("�����û���");
			return false;
		}
	}
}
