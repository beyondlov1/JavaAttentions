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
		 * ActionContext的方式
		 */
		ActionContext context = ActionContext.getContext();
		HttpParameters parameters = context.getParameters();
		Parameter parameter = parameters.get("username");
		context.put("username", parameter.getValue());

		Map<String, Object> session = context.getSession();
		session.put("username1", parameter.getValue());

		/*
		 * ServletActionContext的方式, 直接获取servlet对象
		 */
		HttpServletRequest request = ServletActionContext.getRequest();

		log.info("i am logger");

		return "ok";
	}

	/*
	 * 实现ServletContextAware,setServletRequest...的方式
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
