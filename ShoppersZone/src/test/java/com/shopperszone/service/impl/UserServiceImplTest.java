package com.shopperszone.service.impl;

import org.easymock.EasyMock;
import org.junit.Test;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;

import junit.framework.TestCase;

public class UserServiceImplTest extends TestCase {
	
	private UserDao mockUserDao;
	private UserServiceImpl userServiceImpl;
	
	@Override
	protected void setUp() throws Exception {
		userServiceImpl= new UserServiceImpl();
		mockUserDao = EasyMock.createMock(UserDao.class);
		userServiceImpl.setUserDao(mockUserDao);
	}
	@Override
	protected void tearDown() throws Exception {
		mockUserDao=null;
		userServiceImpl=null;	
	}
	@Test
	public void testAddUser(){
		try{
		User user = new User();
		user.setId(2);
		user.setUsername("jack@gmail.com");
		user.setPassword("jack");
		user.setFirstName("jack");
		user.setLastName("daniel");
		user.setAddress("new york");
		user.setContactNo(12345);
		user.setEnabled(true);
		mockUserDao.saveUser(user);
		EasyMock.expectLastCall().once();
		EasyMock.replay(mockUserDao);
		userServiceImpl.addUser(user);
		EasyMock.verify(mockUserDao);
		}catch(ShoppersZoneException e)
		{
			e.printStackTrace();
		}
	}
	@Test
	public void testGetUserByName() {	
		User user = new User();
		try {
			EasyMock.expect(mockUserDao.findByUserName("jack@gmail.com")).andReturn(user);
			EasyMock.replay(mockUserDao);
			assertEquals(user,userServiceImpl.getUserByName("jack@gmail.com"));
			EasyMock.verify(mockUserDao);
		} catch (ShoppersZoneException e) {
			e.printStackTrace();
		}
		
	}
}
