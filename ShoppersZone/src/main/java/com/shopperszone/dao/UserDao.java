package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.User;

@Repository
public interface UserDao {
	public List<User> listAllUsers() throws ShoppersZoneException;
	
	public void saveUser(User user) throws ShoppersZoneException;

	public User findByUserName(String username) throws ShoppersZoneException;

	public void updateUser(User user) throws ShoppersZoneException;
}
