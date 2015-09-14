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
import com.shopperszone.dao.OrderDao;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.utility.DateUtility;

@SuppressWarnings("unchecked")
@Repository
public class OrderDaoImpl implements OrderDao {

	private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Order saveOrder(User user, List<Item> items) throws ShoppersZoneException {
		Order order = null;
		Double totalAmt = 0.0;
		try {
			Session session = sessionFactory.getCurrentSession();
			order = new Order();
			order.setDate(DateUtility.getCurrentDate());
			order.setUser(user);
			order.setItems(items);
			for (Item item : items) {
				totalAmt += item.getPrice();
			}
			order.setTotalAmt(totalAmt);
			order.setPaymentType("COD");
			session.save(order);
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return order;
	}

	@Override
	public List<Order> findOrdersByUserId(int userId) throws ShoppersZoneException {
		List<Order> myOrders = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			myOrders = session.createQuery("from Order where user_id=" + userId).list();
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return myOrders;
	}
	@Override
	public List<Order> findAllOrders() throws ShoppersZoneException{
		List<Order> allorders = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			allorders = session.createQuery("from Order").list();	
			System.out.println(allorders.size());
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return allorders;
	}

}
