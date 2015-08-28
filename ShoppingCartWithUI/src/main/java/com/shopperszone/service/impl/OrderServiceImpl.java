package com.shopperszone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopperszone.dao.OrderDao;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	public void setItemDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public Order placeOrder(User user, List<Item> items) {
		System.out.println("In service Impl");
		Order order = this.orderDao.placeOrder(user, items);
		return order;
	}

	@Override
	public List<Order> getMyOrders(User currentUser) {
		List<Order> myOrders = this.orderDao.getOrdersForUser(currentUser);
		return myOrders;
	}

	
}
