package com.shopperszone.service;

import org.springframework.stereotype.Service;

import com.shopperszone.model.User;

@Service
public interface UserService {

	public void addUser(User user);

	User getUserByName(String username);

	public void updateUser(User user);

}