package com.shopperszone.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopperszone.custom.exceptions.ShoppersZoneException;
import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;
import com.shopperszone.model.UserRole;

@SuppressWarnings("unchecked")
@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session session = null;
	
	@Override
	public void saveUser(User user) throws ShoppersZoneException {
		try {
			session = sessionFactory.getCurrentSession();
			user.setEnabled(true);
			session.save(user);
			UserRole role = new UserRole();
			role.setRole("ROLE_USER");
			role.setUser(user);
			session.save(role);
			LOG.info("Saved the objects : \n" + user.toString() + "\n" + role.toString());
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
	}

	@Override
	public User findByUserName(String username) throws ShoppersZoneException {
		List<User> users = null;
		try {
			session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User where username = '" + username + "'");
			users = (List<User>) query.list();
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return ((users.isEmpty()) ? null : users.get(0));
	}

	@Override
	public void updateUser(User user) throws ShoppersZoneException {
		try {
			session = sessionFactory.getCurrentSession();
			user.setEnabled(true);
			session.update(user);
			LOG.info("Saved the objects : \n" + user.toString());
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
	}
	
	@Override
	public List<User> listAllUsers() throws ShoppersZoneException {
		List<User> allusers = null;
		try {
			session = sessionFactory.getCurrentSession(); 
			allusers = session.createQuery("from User").list();
		} catch (HibernateException e) {
			LOG.debug("Error : " + e.getClass());
			throw new ShoppersZoneException("Internal Error Occured Try Again");	
		}
		return allusers;
	}
}
