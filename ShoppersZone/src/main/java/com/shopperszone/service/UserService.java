package com.shopperszone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.User;

@Service
public interface UserService {

	public void addUser(User user) throws ShoppersZoneException;

	public User getUserByName(String username) throws ShoppersZoneException;

	public void updateUser(User user) throws ShoppersZoneException;
	
	public List<User> getAllUsers() throws ShoppersZoneException;
	
	boolean isAlreadyRegistered(String id) throws ShoppersZoneException;

}