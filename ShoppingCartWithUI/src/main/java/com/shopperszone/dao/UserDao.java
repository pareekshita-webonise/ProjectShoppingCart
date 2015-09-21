package com.shopperszone.dao;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.User;

@Repository
public interface UserDao {
	public User getCurrentUser();

	public void setCurrentUser(User currentUser);

	public void addUser(User user);

	public int validateUser(User user);
}
