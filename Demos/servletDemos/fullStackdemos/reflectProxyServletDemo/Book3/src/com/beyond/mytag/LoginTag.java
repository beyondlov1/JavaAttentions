package com.beyond.mytag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoginTag extends SimpleTagSupport {

	private String url;
	private String username;
	private String password;
	private String msg = "";

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();
		// JspFragment jspBody = this.getJspBody();// ��ȡ��ǩ���е�����

		// jspBody.invoke(null);// �����ǩ���е�����

		out.write(
				"<form action='" + url + "' method='post'>" + "<span>�û���:</span><input name='username' type='text'><br>"
						+ "<span>����:</span><input name='password' type='password'><br><input type='submit' value='��¼'>"
						+ "<span>" + msg + "</span><br>"
						+ "<input type='checkbox' name='autoLogin' value='autoLogin'><label>�Զ���¼</label></form>");

	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
