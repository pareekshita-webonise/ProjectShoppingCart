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

@SuppressWarnings("unchecked")
@Repository
public class ItemDaoImpl implements ItemDao {

	private static final Logger LOG = LoggerFactory.getLogger(ItemDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Override
	public List<Item> findAll() {
		List<Item> items = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item").list();
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		return items;
	}

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

	@Override
	public List<String> getDistinctCategories() {
		List<String> categories = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			categories = session.createQuery("SELECT distinct category FROM Item").list();
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		return categories;
	}
	@Override
	public void saveItem(Item item){
		try {
			session=sessionFactory.getCurrentSession();
			session.save(item);
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		
	}
	@Override
	public void updateItem(Item item){
		try {
			session=sessionFactory.getCurrentSession();
			session.update(item);
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		
	}
	@Override
	public void deleteItem(int id){
		try {
			session=sessionFactory.getCurrentSession();
			session.createQuery("DELETE FROM Item where id= '" + id + "'");
		} catch (Exception e) {
			LOG.error("Error : " + e);
		}
		
	}
}
