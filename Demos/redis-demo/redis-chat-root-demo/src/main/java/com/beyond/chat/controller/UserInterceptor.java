package com.beyond.chat.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getServletPath().endsWith(".html")||request.getServletPath().endsWith("signin")){
            return true;
        }
        if (checkUser(request)){
            return true;
        }
        response.setStatus(403);
        return false;
    }

    private boolean checkUser(HttpServletRequest request) {
        Cookie foundCookie = getCookie(request,"username");
        return foundCookie != null;
    }

    private Cookie getCookie(HttpServletRequest request,String name) {
        Cookie foundCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)){
                foundCookie = cookie;
                break;
            }
        }
        return foundCookie;
    }

}
