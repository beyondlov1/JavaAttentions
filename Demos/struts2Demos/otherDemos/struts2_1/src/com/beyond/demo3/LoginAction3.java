package com.beyond.demo3;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 封装数据(属性驱动  2 List Map)
 */
public class LoginAction3 extends ActionSupport {

	private static final Logger log = LogManager.getLogger();

	private List<User> list = new ArrayList<>();

	public String execute() {
		log.info(list.get(0).getUsername());
		log.info(list.get(0).getPassword());
		return SUCCESS;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

}
