package com.shopperszone.service;

import org.springframework.stereotype.Service;

import com.shopperszone.model.Inventory;

@Service
public interface ItemService 
{
	Inventory getAllDbItems();
	
	Inventory addItemsToCart(int[]  ids);

	Inventory getCartItems();
	
	Inventory getCategorisedItems(String list);
}
