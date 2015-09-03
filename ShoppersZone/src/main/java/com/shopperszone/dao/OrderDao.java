package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;

@Repository
public interface OrderDao {

	public Order saveOrder(User user, List<Item> items);

	List<Order> findOrdersByUserId(int userId);
}
