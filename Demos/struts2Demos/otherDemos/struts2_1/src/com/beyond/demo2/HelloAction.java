package com.beyond.demo2;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;
import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;

public class HelloAction implements ServletRequestAware {

	private Logger log = LogManager.getLogger();

	private HttpServletRequest request = null;

	public String hello() {

		/*
		 * ActionContext�ķ�ʽ
		 */
		ActionContext context = ActionContext.getContext();
		HttpParameters parameters = context.getParameters();
		Parameter parameter = parameters.get("username");
		context.put("username", parameter.getValue());

		Map<String, Object> session = context.getSession();
		session.put("username1", parameter.getValue());

		/*
		 * ServletActionContext�ķ�ʽ, ֱ�ӻ�ȡservlet����
		 */
		HttpServletRequest request = ServletActionContext.getRequest();

		log.info("i am logger");

		return "ok";
	}

	/*
	 * ʵ��ServletContextAware,setServletRequest...�ķ�ʽ
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
