package com.shopperszone.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shopperszone.dao.ItemDao;
import com.shopperszone.model.Item;
import com.shopperszone.utility.HibernateSessionManager;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemDaoImpl.class);
	
	private List<Item> cartItems;

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAllItems() {
		List<Item> items = null;
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			items = session.createQuery("from Item").list();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			logger.error("Error : "+e);
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getCategorisedItems(String category) {
		List<Item> items = null;
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			items = session.createQuery("from Item where category = '" + category.toLowerCase() + "'").list();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			logger.error("Error : "+e);
		}
		return items;
	}

	public List<Item> getCartItems() {
		if (cartItems == null)
			cartItems = new ArrayList<Item>();
		return cartItems;
	}

	public void setCartItems(List<Item> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public void addToCart(int[] items) {
		if (cartItems == null)
			cartItems = new ArrayList<Item>();
		for (int i = 0; i < items.length; i++) {
			List<Item> item = this.getById(items[i]);
			if (!cartItems.contains(item.get(0)))
				cartItems.add(item.get(0));
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getById(int id) {
		List<Item> items = null;
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			items = session.createQuery("from Item where id = " + id + "").list();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			logger.error("Error : "+e);
		}
		return items;
	}

}
