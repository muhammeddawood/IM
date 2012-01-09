package com.im.dao;

import com.im.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao  {

	@Override
	public User authenticateUser(String username, String password) {
		return (User)find("user from User u where u.username=? and u.password=?", new Object[]{username, password});
	}

	@Override
	public boolean isExistingUser(String username) {
		User user = (User)findUnique("from User u where u.username=?", new Object[]{username});
		return user != null;
	}

}
