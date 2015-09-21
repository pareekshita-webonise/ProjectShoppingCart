package com.shopperszone.dao;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class ShoppingCart 
{
	final static Logger logger = Logger.getLogger(ShoppingCart.class);
	public static void main(String args[])
	{		
		Session session=null;
		try
		{
			session = HibernateSessionManager.getSessionFactory().openSession();
			session.getTransaction().begin();
			Item item1 = (Item)session.get(Item.class, 4);
			Item item2 = (Item)session.get(Item.class, 7);
			
			HashSet<Item> items = new HashSet<Item>();
			items.add(item1);
			items.add(item2);
			User user = (User)session.get(User.class, 3);
			
			Order order = new Order();
			order.setDate("2015-08-21");
			order.setTotalAmt(10000);
			order.setPaymentType("COD");
			order.setUser(user);
			order.setItems(items);
			
			session.save(order);
			session.getTransaction().commit();
			session.close();
		}
		catch(Exception e)
		{
			logger.info(e);
			session.close();
		}
	}
}
