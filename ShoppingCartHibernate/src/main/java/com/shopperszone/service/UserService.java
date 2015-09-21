package com.shopperszone.service;

import org.springframework.stereotype.Service;

import com.shopperszone.model.User;

@Service
public interface UserService 
{
	boolean validateUser(String username, String password);
	public User getCurrentUser();
	boolean signupSubmit(String username,String password,String fname,String lname,int mobile);
	void setCurrentUser(User user);
}
