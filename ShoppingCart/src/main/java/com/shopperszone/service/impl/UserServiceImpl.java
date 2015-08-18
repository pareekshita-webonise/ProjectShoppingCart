package com.shopperszone.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Service;

import com.shopperszone.db.DbConnection;
import com.shopperszone.model.User;
import com.shopperszone.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	User currentUser=null;
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public User getCurrentUser() {
		return currentUser;
	}


	@Override
	public boolean validateUser(String username, String password) 
	{
		boolean res=false;
		try
		{
			Connection connection = new DbConnection().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
			if(resultSet.next())
			{
				res=true;
				currentUser = new User();
				currentUser.setId(resultSet.getInt("id"));
				currentUser.setUserName(resultSet.getString("username"));
				currentUser.setPassword(resultSet.getString("password"));
				currentUser.setFirstName(resultSet.getString("fname"));
				currentUser.setLastName(resultSet.getString("lname"));
				currentUser.setContactNo(resultSet.getInt("mobile"));
				currentUser.display();
				System.out.println("Logged in");
			}
			else
			{
				res=false;
				System.out.println("Error");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	
	@Override
	public boolean signupSubmit(String username, String password, String fname, String lname, int mobile) {
		Connection connection = new DbConnection().getConnection();
		try {
			Statement statement =connection.createStatement();
			String query = " insert into users (username, password, fname, lname, mobile)"
			        + " values ('"+username+"','"+password+"','"+fname+"','"+lname+"',"+mobile+")";
			statement.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
}
