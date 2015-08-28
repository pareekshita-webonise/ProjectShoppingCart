package com.shopperszone.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;
import com.shopperszone.utility.HibernateSessionManager;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private User currentUser = null;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public void addUser(User user) {
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			this.setCurrentUser(user);
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int validateUser(User user) {
		int status = 0;
		try {
			Session session = HibernateSessionManager.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from User where username='" + user.getUserName() + "'");
			System.out.println("My query : " + query.getQueryString());
			List<User> users = query.list();
			System.out.println(users.size());
			if (users.size() == 0)
				status = 1;
			else {
				if (users.get(0).getPassword().equals(user.getPassword())) {
					status = 0;
					this.setCurrentUser(users.get(0));
				} else {
					status = 2;
				}
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Error : " + e);
		}
		return status;
	}
}
