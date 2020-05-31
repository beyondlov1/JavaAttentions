package com.beyond.service;

import java.util.List;

import com.beyond.entity.User;
import com.beyond.exception.NoLoginException;
import com.beyond.exception.NotFoundUserException;
import com.beyond.exception.NullPasswordException;
import com.beyond.exception.NullUsernameException;
import com.beyond.exception.UsernameRepeatException;

public interface UserService {
	int saveUser(User user) throws NullUsernameException, UsernameRepeatException, NullPasswordException;

	int removeUser(String id);

	int modifyUser(User user);

	User findUserById(String id);

	User findUserByName(String name);

	List<User> findAll();

	@Deprecated
	boolean vertifyUser(User user) throws NoLoginException, NotFoundUserException, NullPasswordException;

	boolean vertifyUser(User user, Boolean isEncrypted)
			throws NoLoginException, NotFoundUserException, NullPasswordException;
}
