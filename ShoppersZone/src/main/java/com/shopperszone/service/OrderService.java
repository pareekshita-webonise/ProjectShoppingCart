package com.shopperszone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;

@Service
public interface OrderService {
	public Order placeOrder(User user, List<Item> items) throws ShoppersZoneException;

	public List<Order> getMyOrders(User currentUser) throws ShoppersZoneException;

	public List<Order> getAllOrders() throws ShoppersZoneException;
}
