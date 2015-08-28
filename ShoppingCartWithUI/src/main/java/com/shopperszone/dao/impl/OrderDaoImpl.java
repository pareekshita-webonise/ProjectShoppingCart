package com.shopperszone.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shopperszone.dao.OrderDao;
import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.utility.DateUtility;
import com.shopperszone.utility.HibernateSessionManager;

@Repository
public class OrderDaoImpl implements OrderDao {

	private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

	Order order;

	@Override
	public Order placeOrder(User user, List<Item> items) {
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			order = new Order();
			order.setDate(DateUtility.getCurrentDate());
			order.setUser(user);
			order.setItems(items);
			order.setTotalAmt(10000);
			order.setPaymentType("COD");
			session.save(order);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersForUser(User user) 
	{
		List<Order> myOrders=null;
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			myOrders = session.createQuery("from Order where user_id="+user.getId()).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return myOrders;
	}

}
