package com.shopperszone.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shopperszone.db.DbConnection;
import com.shopperszone.model.Inventory;
import com.shopperszone.model.Item;
import com.shopperszone.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService
{
	private Inventory allDbItems=null;
	private Inventory cartItems=null;
	
	public Inventory getCartItems() {
		return cartItems;
	}

	public void setCartItems(Inventory cartItems) {
		this.cartItems = cartItems;
	}

	public void setAllDbItems(Inventory allDbItems) 
	{
		this.allDbItems = allDbItems;
	}
	
	@Override
	public Inventory getAllDbItems() 
	{
		if(allDbItems==null)
		{
			allDbItems = new Inventory();
			Map<Integer, Item> itemsFromDb = new HashMap<Integer, Item>();
			
			try
			{
				Connection connection = new DbConnection().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");
				while(resultSet.next())
				{
					Item item = new Item();
					item.setName(resultSet.getString("item_name"));
					item.setPrice(resultSet.getDouble("price"));
					item.setQuantity(resultSet.getInt("quantity"));
					item.setCategory(resultSet.getString("category"));
					item.display();
					itemsFromDb.put(resultSet.getInt("id"), item);
					//System.out.println(itemsFromDb.size()+item.getName());
				}
				allDbItems.setItems(itemsFromDb);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return allDbItems;
	}

	@Override
	public Inventory addItemsToCart(int[] ids) 
	{
		Map<Integer, Item> myCart;
		Inventory allInventoryItems = this.getAllDbItems();
		Map<Integer, Item> allItems = allInventoryItems.getItems();
		if(cartItems==null)
		{			
			cartItems = new Inventory();			
			myCart = new HashMap<Integer, Item>();			
		}
		else
		{
			myCart = cartItems.getItems();
		}
		for (int itemId = 0; itemId < ids.length; itemId++) 
		{
			if(!myCart.containsKey(ids[itemId]))
			{
				Item item = allItems.get(ids[itemId]);
				item.setQuantity(1);			
				myCart.put(ids[itemId], item);
			}
		}
		cartItems.setItems(myCart);
		return cartItems;
	}	
	
	@Override
	public Inventory getCategorisedItems(String list) 
	{
		if(list.equals("All"))
			return this.getAllDbItems();
		Inventory allInventoryItems = this.getAllDbItems();
		Inventory categoryItems = new Inventory();
		Map<Integer, Item> allItems = allInventoryItems.getItems();
		Map<Integer, Item> myCart = new HashMap<Integer, Item>();
		for (int i = 1; i <= allItems.size(); i++) 
		{
			Item item = allItems.get(i);
			if(item.getCategory().equals(list))
				myCart.put(i, item);
		}
		categoryItems.setItems(myCart);
		return categoryItems;
	}	
}
