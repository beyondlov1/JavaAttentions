package com.beyond.service;

import java.util.List;

import com.beyond.entity.Category;
import com.beyond.entity.CategoryForSql;
import com.beyond.exception.IllegalArgumentException;

public interface CategoryService {
	int saveCategory(Category category) throws IllegalArgumentException;

	int saveCategory(CategoryForSql category) throws IllegalArgumentException;

	int removeCategory(String id);

	int modifyCategory(Category category);

	Category findCategoryById(String id);

	List<Category> findAll();
}
