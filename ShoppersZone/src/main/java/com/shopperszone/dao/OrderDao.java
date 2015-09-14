package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;

@Repository
public interface OrderDao {

	public Order saveOrder(User user, List<Item> items) throws ShoppersZoneException;

	public List<Order> findOrdersByUserId(int userId) throws ShoppersZoneException;

	public List<Order> findAllOrders() throws ShoppersZoneException;
}
