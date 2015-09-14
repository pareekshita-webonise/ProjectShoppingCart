package com.shopperszone.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
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
	public List<Item> findAll() throws ShoppersZoneException {
		List<Item> items = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item").list();
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return items;
	}

	@Override
	public List<Item> getCategorisedItems(String category) throws ShoppersZoneException {
		List<Item> items = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item where category = '" + category.toLowerCase() + "'").list();
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return items;
	}

	@Override
	public Item findById(int id) throws ShoppersZoneException {
		List<Item> items = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			items = session.createQuery("from Item where id = " + id + "").list();
		}catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return ((items.isEmpty()) ? null : items.get(0));
	}

	@Override
	public List<String> getDistinctCategories() throws ShoppersZoneException {
		List<String> categories = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			categories = session.createQuery("SELECT distinct category FROM Item").list();
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return categories;
	}
	@Override
	public void saveItem(Item item) throws ShoppersZoneException{
		try {
			session=sessionFactory.getCurrentSession();
			session.save(item);
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		
	}
	@Override
	public void updateItem(Item item) throws ShoppersZoneException{
		try {
			session=sessionFactory.getCurrentSession();
			session.update(item);
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		
	}
	@Override
	public void deleteItem(int id) throws ShoppersZoneException{
		try {
			session=sessionFactory.getCurrentSession();
			session.createQuery("DELETE FROM Item where id= '" + id + "'");
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");			
		}
		
	}
}
