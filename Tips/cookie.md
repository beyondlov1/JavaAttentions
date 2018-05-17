# cookie相关

----------

**如果客户端禁用cookie 则可用url重写的方式来解决**

     https://github.com/beyondlov1/JavaAttentions; jsession=JFOJOGJG.....  

[https://blog.csdn.net/wangjian5748/article/details/5096409](https://blog.csdn.net/wangjian5748/article/details/5096409)

----------
**session**  
在request.getSession()时创建

---

**用ThreadLocal进行解耦和**

Servlet为单例多线程, 可将request, response放入ThreadLocalMap中, 之后可在本线程中获得相同的request和response对象

CookieUtils+ThreadLocal+Filter解耦合: 

CookieUtils:

    package com.beyond.utils;

    import java.util.HashMap;
    import java.util.Map;

	import javax.servlet.http.Cookie;
	import javax.servlet.http.HttpServletRequest;

	public class CookieUtils {

	public static Cookie getCookie(String key) {
		HttpServletRequest request = RequestResponseBox.getRequest();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.trim().equals(cookie.getName().trim())) {
					return cookie;
				}
			}
		}
		return null;

	}

	public static String getCookieValue(String key) {
		HttpServletRequest request = RequestResponseBox.getRequest();

		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (key.trim().equals(cookie.getName().trim())) {
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}

	public static Map<String, String> getCookieMap() {
		HttpServletRequest request = RequestResponseBox.getRequest();
		Map<String, String> map = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			map = new HashMap<>();
			for (Cookie cookie : cookies) {
				map.put(cookie.getValue(), cookie.getValue());
			}
		}
		return map;
	}
	}


Fileter:

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

		// 将request和response封装到ThreadLocal中
		RequestResponseBox.setRequest(request);
		RequestResponseBox.setResponse(response);

		HttpSession session = request.getSession();
		if (list.contains(request.getServletPath())) {// 在list白名单中不拦截
			chain.doFilter(request, response);
		} else {// 不在list白名单中的: 拦截
			if (session.getAttribute("user") != null) {// 如果session中有user说明能登陆
														// OK
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

					} catch (Exception e) {// 没登录成功 cookie被修改
											// ,session中未放入user
											// 跳到登录界面
						response.sendRedirect(request.getContextPath() + "/UserServlet?op=initLogin");
					}
				} else { // 没有userinfo的cookie,而且session中没有user: 不能登录
					response.sendRedirect(request.getContextPath() + "/UserServlet?op=initLogin");
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		list.add("/UserServlet");
	}

	@Override
	public void destroy() {
	}

}
