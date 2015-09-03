package com.shopperszone.dao;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.User;

@Repository
public interface UserDao {
	public void saveUser(User user);

	public User findByUserName(String username);
}
