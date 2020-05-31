package com.beyond.filter;

import java.io.IOException;

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

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter") // 暂时关闭filter
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {// 如果session中有user说明能登陆 OK
			chain.doFilter(request, response);
		} else {// 如果session中没有user

			// 查询cookie中有没有userinfo
			String userinfo = CookieUtils.getCookieValue("userinfo");

			if (userinfo != null) {// userinfo不为空:自动登录
				// 解析登录的账户密码并验证
				UserService us = (UserService) ServiceFactory.getInstance().getService(UserServiceImpl.class);
				try {
					JSONObject json = new JSONObject(userinfo);
					String username = (String) json.get("username");
					String password = (String) json.get("password");
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					us.vertifyUser(user, true);

					user = us.findUserByName(user.getUsername());// 写入完整的user(不一定有用)
					session.setAttribute("user", user); // 如果登录成功在session中加入user

					chain.doFilter(request, response);

				} catch (Exception e) {// 没登录成功 cookie被修改 ,session中未放入user,直接放行
										// 跳到登录界面
					e.printStackTrace();
					chain.doFilter(request, response);
				}
			} else { // 没有userinfo的cookie,而且session中没有user: 不能登录
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
