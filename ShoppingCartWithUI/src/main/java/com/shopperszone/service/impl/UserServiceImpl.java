package com.shopperszone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;
import com.shopperszone.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	public int validateUser(User user) {
		return this.getUserDao().validateUser(user);
	}
}
