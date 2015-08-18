package com.shopperszone.model;

public class User 
{
	private int id;	
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private int contactNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public void display() 
	{
		// TODO Auto-generated method stub
		System.out.println("Username : "+userName);
		System.out.println("Password : "+password);
		System.out.println("FirstName : "+firstName);
		System.out.println("LastName : "+lastName);
		System.out.println("Contact : "+contactNo);
	}	
}
