package com.beyond.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.beyond.entity.User;
import com.beyond.proxy.ServiceFactory;
import com.beyond.service.UserService;
import com.beyond.service.impl.UserServiceImpl;
import com.beyond.utils.CookieUtils;
import com.beyond.utils.RequestResponseBox;

/**
 * Servlet Filter implementation class ListFilter
 */
@WebFilter("/*")
public class ListFilter implements Filter {

	private List<String> list = new ArrayList<>();

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// ��request��response��װ��ThreadLocal��
		RequestResponseBox.setRequest(request);
		RequestResponseBox.setResponse(response);

		HttpSession session = request.getSession();
		if (list.contains(request.getServletPath())) {// ��list�������в�����
			chain.doFilter(request, response);
		} else {// ����list�������е�: ����
			if (session.getAttribute("user") != null) {// ���session����user˵���ܵ�½
														// OK
				chain.doFilter(request, response);
			} else {// ���session��û��user

				// ��ѯcookie����û��userinfo
				String userinfo = CookieUtils.getCookieValue("userinfo");

				if (userinfo != null) {// userinfo��Ϊ��:�Զ���¼
					// ������¼���˻����벢��֤
					UserService us = (UserService) ServiceFactory.getInstance().getService(UserServiceImpl.class);
					try {
						JSONObject json = new JSONObject(userinfo);
						String username = (String) json.get("username");
						String password = (String) json.get("password");
						User user = new User();
						user.setUsername(username);
						user.setPassword(password);
						us.vertifyUser(user, true);

						user = us.findUserByName(user.getUsername());// д��������user(��һ������)
						session.setAttribute("user", user); // �����¼�ɹ���session�м���user

						chain.doFilter(request, response);

					} catch (Exception e) {// û��¼�ɹ� cookie���޸�
											// ,session��δ����user
											// ������¼����
						response.sendRedirect(request.getContextPath() + "/UserServlet?op=initLogin");
					}
				} else { // û��userinfo��cookie,����session��û��user: ���ܵ�¼
					response.sendRedirect(request.getContextPath() + "/UserServlet?op=initLogin");
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		list.add("/UserServlet");
		list.add("/CartServlet");
		list.add("/test");
	}

	@Override
	public void destroy() {
	}

}