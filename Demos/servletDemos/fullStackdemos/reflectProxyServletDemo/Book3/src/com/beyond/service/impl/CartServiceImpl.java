package com.beyond.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beyond.entity.Book;
import com.beyond.service.CartService;
import com.beyond.utils.CookieUtils;
import com.beyond.utils.RequestResponseBox;

public class CartServiceImpl implements CartService {

	@Override
	public int putInCart(Book book) {
		HttpServletRequest request = RequestResponseBox.getRequest();
		HttpServletResponse response = RequestResponseBox.getResponse();

		String id = book.getId();

		String value = CookieUtils.getCookieValue("cart");
		String newValue = "";
		boolean isFound = false;
		if (value != null) {
			String[] eachBook = value.split("\\-");
			System.out.println(eachBook[0]);
			for (String bookStr : eachBook) {
				String bookId = bookStr.split("\\_")[0];
				String bookNum = bookStr.split("\\_")[1];
				newValue += "-";
				if (bookId.equals(id)) {
					newValue += id + "_" + (Integer.parseInt(bookNum) + 1);
					isFound = true;
				} else {
					newValue += bookId + "_" + bookNum;
				}
			}

			if (!isFound) {
				newValue += "-" + id + "_" + 1;
			}
		} else {
			newValue += "-" + id + "_" + 1;
		}

		System.out.println(newValue.substring(1));

		Cookie cookie = new Cookie("cart", newValue.substring(1));
		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);

		return 0;
	}

	@Override
	public Map<String, String> findAllCart() {
		String value = CookieUtils.getCookieValue("cart");
		Map<String, String> map = new HashMap<>();

		if (value != null) {
			String[] eachBook = value.split("\\-");
			for (String bookStr : eachBook) {
				String bookId = bookStr.split("\\_")[0];
				String bookNum = bookStr.split("\\_")[1];
				map.put(bookId, bookNum);
			}
			return map;
		} else {
			return null;
		}
	}

}
