package com.shopperszone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.model.Item;

@Service
public interface ItemService {

	public List<Item> getAllItems();

	public List<Item> getCategorisedItems(String category);

	public List<String> getAllCategories();

	public List<Item> addToCart(List<Item> cartItems, int[] items);

	public List<Item> deleteItemsFromCart(List<Item> cartItems, int[] items);

	public void addItem(Item item);

	public void updateItem(Item item);

	public void deleteItem(int id);

	public Item getItemById(int id);
}
