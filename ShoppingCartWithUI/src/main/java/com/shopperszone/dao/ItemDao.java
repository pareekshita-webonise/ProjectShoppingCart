package com.shopperszone.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shopperszone.model.Item;

@Repository
public interface ItemDao {

	public List<Item> getById(int id);

	public List<Item> getAllItems();

	public List<Item> getCategorisedItems(String category);

	public void addToCart(int[] items);

	public List<Item> getCartItems();

	public void setCartItems(List<Item> cartItems);
}
