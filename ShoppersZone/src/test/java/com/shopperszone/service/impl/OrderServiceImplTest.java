package com.shopperszone.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import com.shopperszone.dao.OrderDao;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;

import junit.framework.TestCase;

public class OrderServiceImplTest extends TestCase{
	
	private OrderDao mockOrderDao;
	private OrderServiceImpl orderServiceImpl;
	
	@Override
	protected void setUp() throws Exception{
		orderServiceImpl= new OrderServiceImpl();
		/*mockUserDao =EasyMock.createMock(UserDao.class);*/
		mockOrderDao=EasyMock.createMock(OrderDao.class);
		/*mockItemDao=EasyMock.createMock(ItemDao.class);*/
		orderServiceImpl.setOrderDao(mockOrderDao);
	}
	@Override
	protected void tearDown(){
		mockOrderDao=null;
		orderServiceImpl=null;
	}
	@Test
	public void testplaceOrder(){
		Order order = new Order();
		User user = new User();
		List<Item> items = new ArrayList<Item>();
		user.setId(2);
		EasyMock.expect(mockOrderDao.saveOrder(user, items)).andReturn(order);
		EasyMock.replay(mockOrderDao);
		assertEquals(order, orderServiceImpl.placeOrder(user, items));
		EasyMock.verify(mockOrderDao);
	}
	@Test
	public void testplaceOrderNullItems(){
		User user = new User();
		user.setId(2);
		EasyMock.expect(mockOrderDao.saveOrder(user, null)).andReturn(null);
		EasyMock.replay(mockOrderDao);
		assertEquals(null, orderServiceImpl.placeOrder(user, null));		
	}
	@Test
	public void testplaceOrderNullUser(){
		List<Item> items = new ArrayList<Item>();
		EasyMock.expect(mockOrderDao.saveOrder(null, items)).andReturn(null);
		EasyMock.replay(mockOrderDao);
		assertEquals(null, orderServiceImpl.placeOrder(null, items));		
	}
	@Test
	public void testgetMyOrders(){
		User user= new User();
		user.setId(2);
		user.setUsername("jack@gmail.com");
		user.setPassword("jack");
		user.setFirstName("jack");
		user.setLastName("daniel");
		user.setAddress("new york");
		user.setContactNo(12345);
		user.setEnabled(true);
		List<Order> myOrders = new ArrayList<Order>();
		EasyMock.expect(mockOrderDao.findOrdersByUserId(2)).andReturn(myOrders);
		EasyMock.replay(mockOrderDao);
		assertEquals(myOrders,orderServiceImpl.getMyOrders(user));
		EasyMock.verify(mockOrderDao);		
	}
}
