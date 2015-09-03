package com.shopperszone.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopperszone.dao.UserDao;
import com.shopperszone.model.User;
import com.shopperszone.model.UserRole;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session session = null;

	@Override
	public void saveUser(User user) {
		try{
		session = sessionFactory.getCurrentSession();
		user.setEnabled(true);
		session.save(user);
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		session.save(role);
		LOG.info("Saved the objects : \n" + user.toString() + "\n" + role.toString());
		}catch(Exception e)
		{
			LOG.error(e.getMessage());
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUserName(String username) {
		List<User> users=null;
		try{
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username = '" + username + "'");
		users = (List<User>)query.list();
	}catch(Exception e)
	{
		LOG.error(e.getMessage());
	}
		return ((users.isEmpty())?null:users.get(0));
	}
}
