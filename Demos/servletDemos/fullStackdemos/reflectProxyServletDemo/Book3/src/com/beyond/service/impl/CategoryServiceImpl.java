package com.beyond.service.impl;

import java.util.List;

import com.beyond.dao.BaseDao;
import com.beyond.entity.Category;
import com.beyond.entity.CategoryForSql;
import com.beyond.exception.IllegalArgumentException;
import com.beyond.proxy.DAOFactory;
import com.beyond.service.CategoryService;
import com.beyond.utils.IdUtils;

public class CategoryServiceImpl implements CategoryService {

	private BaseDao dao = DAOFactory.getInstance().getBaseDao();

	@Override
	public int saveCategory(Category category) throws IllegalArgumentException {
		// 1. 验证category中的参数是否符合保存要求
		String name = category.getName();

		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("不合法输入");
		}

		// 2. 设置id
		category.setId(IdUtils.getId());

		return dao.addBean(category);
	}

	@Override
	public int saveCategory(CategoryForSql category) throws IllegalArgumentException {
		// 1. 验证category中的参数是否符合保存要求
		String name = category.getName();
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("不合法输入");
		}

		// 2. 设置id
		category.setId(IdUtils.getId());

		return dao.addBean(category);
	}

	@Override
	public int removeCategory(String id) {
		return dao.deleteBean(Category.class, id);
	}

	@Override
	public int modifyCategory(Category category) {
		return dao.updateBean(category);
	}

	@Override
	public Category findCategoryById(String id) {
		return dao.selectBean(Category.class, id);

	}

	@Override
	public List<Category> findAll() {
		return dao.selectAllBean(Category.class);
	}

}
