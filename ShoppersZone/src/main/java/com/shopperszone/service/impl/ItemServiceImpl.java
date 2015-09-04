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

	List<String> categories = null;

	@Override
	public List<Item> getAllItems() {
		return this.itemDao.findAll();
	}

	@Override
	public List<Item> getCategorisedItems(String category) {
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
		for (int i = 0; i < items.length; i++) {
			Item item = itemDao.findById(items[i]);
			if (!cartItems.contains(item))
				cartItems.add(item);
		}
		return cartItems;
	}

	@Override
	public List<Item> deleteItemsFromCart(List<Item> cartItems, int[] items) {
		for (int i = 0; i < items.length; i++) {
			for (int k = 0; k < cartItems.size(); k++) {
				Item cItem = cartItems.get(k);
				if (cItem.getId() == items[i]) {
					cartItems.remove(k);
				}
			}
		}
		return cartItems;
	}

	public void setItemDao(ItemDao mockItemDao) {
		this.itemDao = mockItemDao;

	}

}
