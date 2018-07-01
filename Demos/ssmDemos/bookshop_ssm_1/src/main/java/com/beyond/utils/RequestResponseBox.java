package com.beyond.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestResponseBox {

    private static ThreadLocal<HttpServletRequest> requestBox = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseBox = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        requestBox.set(request);
    }

    public static void setResponse(HttpServletResponse response) {
        responseBox.set(response);

    }

    public static HttpServletRequest getRequest() {
        return requestBox.get();
    }

    public static HttpServletResponse getResponse() {
        return responseBox.get();
    }
}
