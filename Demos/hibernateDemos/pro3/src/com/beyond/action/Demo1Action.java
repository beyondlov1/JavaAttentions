package com.beyond.action;

import java.util.List;

import com.beyond.dao.Dao;
import com.beyond.entity.Customer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Demo1Action extends ActionSupport {

	public String findAll() {

		Dao dao = new Dao();

		List<Customer> list = dao.selectAll();

		ActionContext context = ActionContext.getContext();
		context.put("list", list);

		return "findAll";
	}
}
