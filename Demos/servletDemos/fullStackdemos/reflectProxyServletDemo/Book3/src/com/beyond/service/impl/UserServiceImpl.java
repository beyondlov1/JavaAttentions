package com.beyond.service.impl;

import java.util.List;

import com.beyond.dao.BaseDao;
import com.beyond.entity.User;
import com.beyond.exception.NoLoginException;
import com.beyond.exception.NotFoundUserException;
import com.beyond.exception.NullPasswordException;
import com.beyond.exception.NullUsernameException;
import com.beyond.exception.UsernameRepeatException;
import com.beyond.proxy.DAOFactory;
import com.beyond.service.UserService;
import com.beyond.utils.IdUtils;
import com.beyond.utils.SecureUtils;

public class UserServiceImpl implements UserService {
	private BaseDao dao = DAOFactory.getInstance().getBaseDao();

	@Override
	public int saveUser(User user) throws NullUsernameException, UsernameRepeatException, NullPasswordException {
		String username = null;
		String password = null;

		username = user.getUsername();
		password = user.getPassword();

		// 1. 判断输入是否合法
		// 判断姓名是否为空
		if (username == null || "".equals(username)) {
			throw new NullUsernameException("用户名不能为空");
		}

		// 判断是否重名
		User sqlUser = dao.selectBean(User.class, "username", username);
		if (sqlUser != null) {
			throw new UsernameRepeatException("用户名重复");
		}

		// 判断密码是否为空
		if (password == null || "".equals(password)) {
			throw new NullPasswordException("密码为空");
		}

		// 2. 增加用户
		user.setId(IdUtils.getId());
		user.setPassword(SecureUtils.getEncryptString(password));
		return dao.addBean(user);
	}

	@Override
	public int removeUser(String id) {
		return dao.deleteBean(User.class, id);
	}

	@Override
	public int modifyUser(User user) {

		return dao.updateBean(user);
	}

	@Override
	public User findUserById(String id) {

		return dao.selectBean(User.class, id);
	}

	@Override
	public List<User> findAll() {

		return dao.selectAllBean(User.class);
	}

	@Deprecated
	@Override
	public boolean vertifyUser(User user) throws NoLoginException, NotFoundUserException, NullPasswordException {
		boolean isUserAccessible = false;
		String id = null;
		String username = null;
		String password = null;
		User sqlUser = null;

		id = user.getId();
		username = user.getUsername();
		password = user.getPassword();

		password = SecureUtils.getEncryptString(password);

		sqlUser = dao.selectBean(User.class, "username", username);

		if (username == null || "".equals(username) || password == null || "".equals(password)) {
			throw new NoLoginException("请输入登录信息");
		}

		if (sqlUser == null) {
			throw new NotFoundUserException("未找到用户");
		}

		isUserAccessible = username.equals(sqlUser.getUsername()) && password.equals(sqlUser.getPassword());

		if (isUserAccessible == false) {
			throw new NoLoginException("用户名或密码不正确");
		}

		// 另一种方法 判断能否取到user: 需要两个查询条件 username和password(未写)

		return isUserAccessible;
	}

	public boolean vertifyUser(User user, Boolean isEncrypted)
			throws NoLoginException, NotFoundUserException, NullPasswordException {
		boolean isUserAccessible = false;
		String id = null;
		String username = null;
		String password = null;
		User sqlUser = null;

		id = user.getId();
		username = user.getUsername();
		password = user.getPassword();

		if (!isEncrypted) {
			password = SecureUtils.getEncryptString(password);
		}
		sqlUser = dao.selectBean(User.class, "username", username);

		if (username == null || "".equals(username) || password == null || "".equals(password)) {
			throw new NoLoginException("请输入登录信息");
		}

		if (sqlUser == null) {
			throw new NotFoundUserException("未找到用户");
		}

		isUserAccessible = username.equals(sqlUser.getUsername()) && password.equals(sqlUser.getPassword());

		if (isUserAccessible == false) {
			throw new NoLoginException("用户名或密码不正确");
		}

		// 另一种方法 判断能否取到user: 需要两个查询条件 username和password(未写)

		return isUserAccessible;
	}

	@Override
	public User findUserByName(String name) {
		return dao.selectBean(User.class, "username", name);

	}

}
