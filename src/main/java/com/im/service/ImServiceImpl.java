package com.im.service;

import com.im.User;
import com.im.dao.FriendDao;
import com.im.dao.UserDao;

public class ImServiceImpl implements ImService {
	private UserDao userDao;
	private FriendDao friendDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}
	
	public String authenticateUser(String userName, String password) {
		userDao.authenticateUser(userName, password);
		return null;
	}
	
	public String signUpUser(String username, String password, String email) {
		
		boolean userExists = userDao.isExistingUser(username);
		
		if(userExists) {
			return "2";
		}
		
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		
		userDao.save(user);
		
		return "1";
	}

}
