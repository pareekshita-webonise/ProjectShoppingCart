package com.shopperszone.service.impl;

import java.util.List;

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
	public List<User> getAllUsers() {
		return userDao.listAllUsers();
	}
	
	@Override
	public void addUser(User user) {
		userDao.saveUser(user);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public boolean isAlreadyRegistered(User registeredUser) {
		User user = userDao.findByUserName(registeredUser.getUsername());
		return ((user == null) ? false : true);
	}

	public void setUserDao(UserDao mockUserDao) {
		this.userDao = mockUserDao;
	}

}
