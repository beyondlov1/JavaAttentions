package com.beyond.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;

public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void addUser(User user) {
		hibernateTemplate.save(user);
	}

	public void deleteUser(User user) {
		hibernateTemplate.delete(user);
	}

	public void updateUser(User user) {
		hibernateTemplate.update(user);
	}

	public User selectById(String id) {
		return hibernateTemplate.load(User.class, id);
	}

	public List<User> selectAll() {
		return hibernateTemplate.findByExample(new User());
	}

	@Override
	public User selectByExample(User user) {

		List<User> list = hibernateTemplate.findByExample(user);
		if (list.isEmpty()) {
			return null;
		}

		System.out.println(list.get(0).getId());
		return list.get(0);
	}
}
