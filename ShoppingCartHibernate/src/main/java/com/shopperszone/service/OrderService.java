package com.shopperszone.service;

import org.springframework.stereotype.Service;

import com.shopperszone.model.Inventory;
import com.shopperszone.model.User;


@Service
public interface OrderService 
{
	public boolean placeOrder(User user, Inventory myCart);
}
