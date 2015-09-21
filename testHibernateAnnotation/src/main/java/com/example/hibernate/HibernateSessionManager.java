package com.example.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateSessionManager {
	public static final SessionFactory sessionFactory = buildSessionFactory();
	
	public static SessionFactory buildSessionFactory(){
		try {
			return new AnnotationConfiguration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
