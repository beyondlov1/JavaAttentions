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
