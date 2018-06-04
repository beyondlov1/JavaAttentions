package com.beyond.action;

import com.beyond.service.BaseService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction<T> extends ActionSupport {

	private static final long serialVersionUID = 6070616809961305408L;

	private BaseService<T> baseService;

	public void setBaseService(BaseService<T> baseService) {
		this.baseService = baseService;
	}

}
