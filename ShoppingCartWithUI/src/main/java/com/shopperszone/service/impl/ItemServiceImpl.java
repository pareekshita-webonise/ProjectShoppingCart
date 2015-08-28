package com.shopperszone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopperszone.dao.ItemDao;
import com.shopperszone.model.Item;
import com.shopperszone.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public List<Item> getAllItems() {
		return this.itemDao.getAllItems();
	}

	@Override
	public List<Item> getCategorisedItems(String category) {
		return this.itemDao.getCategorisedItems(category);
	}

	@Override
	public void addToCart(int[] items) {
		this.itemDao.addToCart(items);
	}

	@Override
	public void setCartItems(List<Item> cartItems) {
		this.itemDao.setCartItems(cartItems);

	}

	@Override
	public List<Item> getCartItems() {
		return this.itemDao.getCartItems();
	}
}
