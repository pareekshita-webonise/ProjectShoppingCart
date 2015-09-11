package com.shopperszone.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;

@Service
public interface OrderService {
	public Order placeOrder(User user, List<Item> items);

	public List<Order> getMyOrders(User currentUser);

	public ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName);

	public List<Order> getAllOrders();
}
