package com.shopperszone.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopperszone.dao.OrderDao;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	public void setItemDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public Order placeOrder(User user, List<Item> items) {
		Order order = orderDao.saveOrder(user, items);
		return order;
	}

	@Override
	public List<Order> getMyOrders(User user) {
		List<Order> myOrders = orderDao.findOrdersByUserId(user.getId());
		return myOrders;
	}

	public void setOrderDao(OrderDao mockOrderDao) {
		this.orderDao = mockOrderDao;
	}

}
