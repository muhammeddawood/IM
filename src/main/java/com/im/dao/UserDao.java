package com.im.dao;

import com.im.User;

public interface UserDao extends BaseDao {

	User authenticateUser(String userName, String password);

	boolean isExistingUser(String username);

}
