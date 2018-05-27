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

		// 1. �ж������Ƿ�Ϸ�
		// �ж������Ƿ�Ϊ��
		if (username == null || "".equals(username)) {
			throw new NullUsernameException("�û�������Ϊ��");
		}

		// �ж��Ƿ�����
		User sqlUser = dao.selectBean(User.class, "username", username);
		if (sqlUser != null) {
			throw new UsernameRepeatException("�û����ظ�");
		}

		// �ж������Ƿ�Ϊ��
		if (password == null || "".equals(password)) {
			throw new NullPasswordException("����Ϊ��");
		}

		// 2. �����û�
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
			throw new NoLoginException("�������¼��Ϣ");
		}

		if (sqlUser == null) {
			throw new NotFoundUserException("δ�ҵ��û�");
		}

		isUserAccessible = username.equals(sqlUser.getUsername()) && password.equals(sqlUser.getPassword());

		if (isUserAccessible == false) {
			throw new NoLoginException("�û��������벻��ȷ");
		}

		// ��һ�ַ��� �ж��ܷ�ȡ��user: ��Ҫ������ѯ���� username��password(δд)

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
			throw new NoLoginException("�������¼��Ϣ");
		}

		if (sqlUser == null) {
			throw new NotFoundUserException("δ�ҵ��û�");
		}

		isUserAccessible = username.equals(sqlUser.getUsername()) && password.equals(sqlUser.getPassword());

		if (isUserAccessible == false) {
			throw new NoLoginException("�û��������벻��ȷ");
		}

		// ��һ�ַ��� �ж��ܷ�ȡ��user: ��Ҫ������ѯ���� username��password(δд)

		return isUserAccessible;
	}

	@Override
	public User findUserByName(String name) {
		return dao.selectBean(User.class, "username", name);

	}

}
