package com.shopperszone.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory 
{
	private Map<Integer, String> item = new HashMap<Integer, String>();
	private Map<Integer, Item> items = new HashMap<Integer, Item>();
	
	public Map<Integer, Item> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Item> items) {
		this.items = items;
	}

	public void setDefaults()
	{
		item.put(1, "Xperia Z");
		item.put(2, "Micromax Eureka");
		item.put(3, "Microsoft 730");
		item.put(4, "Samsung Note 3");
		item.put(5, "Xiomi Redme MI");
	}

	public Map<Integer, String> getItem() {
		return item;
	}

	public void setItem(Map<Integer, String> item) {
		this.item = item;
	}
	
}
