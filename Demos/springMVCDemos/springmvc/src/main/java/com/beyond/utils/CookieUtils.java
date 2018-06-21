package com.beyond.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {

	public static String getCookie(String key) {
		String value = null;
		Boolean isFound = false;
		try {
			Cookie[] cookies = RequestResponseBox.getRequest().getCookies();
			if (cookies != null && cookies.length != 0) {
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					if (key.equals(name)) {
						value = cookie.getValue();
						isFound = true;
						break;
					}
				}
			}
			if (isFound) {
				value = URLDecoder.decode(value, "UTF-8");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static void setCookie(String key, String value) {
		Cookie cookie = null;
		try {
			cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(30 * 24 * 60 * 60);
			cookie.setPath(RequestResponseBox.getRequest().getContextPath());
			HttpServletResponse response = RequestResponseBox.getResponse();
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
