package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.User;

@Repository
public interface UserDao {
	public List<User> listAllUsers();
	
	public void saveUser(User user);

	public User findByUserName(String username);

	public void updateUser(User user);
}
