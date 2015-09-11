package com.shopperszone.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopperszone.cacheservice.CacheService;
import com.shopperszone.dao.ItemDao;
import com.shopperszone.model.Item;
import com.shopperszone.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	private static final Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private CacheService cacheService;

	private List<String> categories = null;

	@Override
	public List<Item> getAllItems() {
		return itemDao.findAll();
	}
	
	@Override
	public Item getItemById(int id){
		 Item item = itemDao.findById(id);
		 return item;
	}
	
	@Override
	public List<Item> getCategorisedItems(String category) {
		boolean noSuchCategory=true;
		for(String myCategory:categories)
		{
			if(myCategory.equalsIgnoreCase(category))
				noSuchCategory=false;
		}
		if(noSuchCategory)
			return null;
		
		List<Item> items = new ArrayList<Item>();
		long start = System.currentTimeMillis();
		
		Map<Object, Object> cachedItems = cacheService.readAllHash(category);

		if (cachedItems == null || cachedItems.size() == 0) {
			LOG.info("No data in cache");
			items = this.itemDao.getCategorisedItems(category);
			for (Item item : items) {
				cacheService.save(item);
			}
		} else {
			LOG.info("Data retrieved from cache");
			Set<Object> keys = cachedItems.keySet();
			for (Iterator<Object> iterator = (Iterator<Object>) keys.iterator(); iterator.hasNext();) {
				items.add((Item) cachedItems.get(iterator.next()));
			}
		}
		LOG.info("All items retrieved in : " + (System.currentTimeMillis() - start) + "ms");
		return items;
	}

	@Override
	public List<String> getAllCategories() {
		if (categories == null) {
			categories = itemDao.getDistinctCategories();
			LOG.info(categories.size() + " Categories retrieved from database");
		}
		return categories;
	}

	@Override
	public List<Item> addToCart(List<Item> cartItems, int[] items) {
		boolean present=false;
		for (int i = 0; i < items.length; i++) {
			for(Item item:cartItems)
			{
				if(item.getId()==items[i])
					present=true;
			}
			if(!present)
				cartItems.add(itemDao.findById(items[i]));
		}
		return cartItems;
	}

	@Override
	public List<Item> deleteItemsFromCart(List<Item> cartItems, int[] items) {
		for (int index = 0; index < items.length; index++) {
			for(Item item:cartItems)
			{
				if(item.getId() == items[index])
				{
					cartItems.remove(item);
					break;
				}
			}
		}
		LOG.info("Cart size : "+cartItems.size());
		return cartItems;
	}
	@Override
	public void addItem(Item item){
		itemDao.saveItem(item);
	}
	
	@Override
	public void updateItem(Item item){
		itemDao.updateItem(item);
	}
	
	@Override
	public void deleteItem(int id){
		itemDao.deleteItem(id);
	}
	public void setItemDao(ItemDao mockItemDao) {
		this.itemDao = mockItemDao;
	}
}
