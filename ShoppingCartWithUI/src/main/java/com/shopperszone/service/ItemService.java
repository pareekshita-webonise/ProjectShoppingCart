package com.shopperszone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.model.Item;

@Service
public interface ItemService {
	public List<Item> getAllItems();

	public List<Item> getCategorisedItems(String category);

	public void addToCart(int[] items);

	public void setCartItems(List<Item> cartItems);

	public List<Item> getCartItems();

}
