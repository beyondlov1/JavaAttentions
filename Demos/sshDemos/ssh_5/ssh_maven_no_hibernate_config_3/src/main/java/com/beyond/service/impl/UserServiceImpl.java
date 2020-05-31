package com.beyond.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beyond.dao.impl.UserDaoImpl;
import com.beyond.entity.Page;
import com.beyond.entity.User;
import com.beyond.f.F;

@Service
public class UserServiceImpl {

	private UserDaoImpl userDaoImpl;

	public void save(User user) {

		userDaoImpl.add(user);

	}

	public User find(User user) {
		User foundUser = userDaoImpl.selectByExample(user);
		return foundUser;
	}

	public User find(String id) {
		User foundUser = userDaoImpl.selectById(id);
		return foundUser;
	}

	public List<User> findAll() {
		List<User> foundUsers = userDaoImpl.selectAll();
		return foundUsers;
	}

	public Boolean isUsernameExist(String username) {
		User user = new User();
		user.setUsername(username);
		User find = this.find(user);
		if (find != null) {
			return true;
		}
		return false;
	}

	public Page<User> findAllByPage(Integer currentPageNum) {
		Long totalRecords = userDaoImpl.count(User.class);
		if (currentPageNum == null) {
			currentPageNum = 1;
		}
		Page<User> page = new Page<User>(currentPageNum, totalRecords, F.RECORDS_PER_PAGE, F.DISPLAY_PAGE_COUNT);
		List<User> contentList = userDaoImpl.selectAllByPage(User.class, page);
		page.setContentList(contentList);
		return page;
	}

	public void setUserDaoImpl(UserDaoImpl userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
}
