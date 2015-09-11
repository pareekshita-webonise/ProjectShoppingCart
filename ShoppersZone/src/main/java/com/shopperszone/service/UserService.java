package com.shopperszone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.model.User;

@Service
public interface UserService {

	public void addUser(User user);

	public User getUserByName(String username);

	public void updateUser(User user);
	
	public List<User> getAllUsers();
	
	boolean isAlreadyRegistered(User user);

}