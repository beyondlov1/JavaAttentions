package com.beyond.servlet.reflect;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long begin = System.currentTimeMillis();

		// �����������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// ��ò���ָ��
		String operation = request.getParameter("op");
		Class<?> c = this.getClass();
		Method method = null;

		// ��ȡ����ָ���Ӧ�ķ���
		try {
			method = c.getMethod(operation, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("�����" + method + "���������ڣ�");
		}

		// ִ�з���
		try {
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("servlet:" + method.getName() + " " + (end - begin));
	}
}
