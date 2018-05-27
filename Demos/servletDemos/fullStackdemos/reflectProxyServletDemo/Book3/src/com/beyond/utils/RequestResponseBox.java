package com.beyond.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestResponseBox {
	private static ThreadLocal<HttpServletRequest> requestBox = new ThreadLocal<>();
	private static ThreadLocal<HttpServletResponse> responseBox = new ThreadLocal<>();

	public static HttpServletRequest getRequest() {
		return requestBox.get();
	}

	public static void setRequest(HttpServletRequest request) {
		RequestResponseBox.requestBox.set(request);
	}

	public static HttpServletResponse getResponse() {
		return responseBox.get();
	}

	public static void setResponse(HttpServletResponse response) {
		RequestResponseBox.responseBox.set(response);
	}

}
