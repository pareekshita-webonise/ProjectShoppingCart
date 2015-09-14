package com.shopperszone.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.model.Item;

@Service
public interface ItemService {

	public List<Item> getAllItems() throws ShoppersZoneException;

	public List<Item> getCategorisedItems(String category) throws ShoppersZoneException;

	public List<String> getAllCategories() throws ShoppersZoneException;

	public List<Item> addToCart(List<Item> cartItems, int[] items) throws ShoppersZoneException;

	public List<Item> deleteItemsFromCart(List<Item> cartItems, int[] items) throws ShoppersZoneException;

	public void addItem(Item item) throws ShoppersZoneException;

	public void updateItem(Item item) throws ShoppersZoneException;

	public void deleteItem(int id) throws ShoppersZoneException;

	public Item getItemById(int id) throws ShoppersZoneException;
}
