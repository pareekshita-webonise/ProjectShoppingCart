package com.shopperszone.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopperszone.dao.ItemDao;
import com.shopperszone.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {

	private static final Logger LOG = LoggerFactory.getLogger(ItemDaoImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	private Session session = null;

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll() {
		List<Item> items = null;
		try {
			session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item").list();
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getCategorisedItems(String category) {
		List<Item> items = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item where category = '" + category.toLowerCase() + "'").list();
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		return items;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Item findById(int id) {
		List<Item> items = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item where id = " + id + "").list();
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		return ((items.isEmpty()) ? null : items.get(0));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDistinctCategories() {
		List<String> categories = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("SELECT distinct category FROM Item");
			categories = query.list();
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		return categories;
	}
}
