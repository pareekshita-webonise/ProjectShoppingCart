package com.shopperszone.service;

import org.springframework.stereotype.Service;

import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;

@Service
public interface UserService {
	public UserDao getUserDao();

	public void setUserDao(UserDao userDao);

	public void addUser(User user);

	public int validateUser(User user);
}