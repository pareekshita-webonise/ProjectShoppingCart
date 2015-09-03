package com.shopperszone.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;
import com.shopperszone.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {

		userDao.saveUser(user);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.findByUserName(username);
	}
}
