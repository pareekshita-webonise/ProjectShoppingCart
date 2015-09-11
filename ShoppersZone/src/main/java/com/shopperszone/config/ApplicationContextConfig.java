package com.shopperszone.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.shopperszone.model.Item;
import com.shopperszone.model.Order;
import com.shopperszone.model.User;
import com.shopperszone.model.UserRole;

@Configuration
@ComponentScan("com.shopperszone")
@EnableTransactionManagement
public class ApplicationContextConfig {	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/shopping_cart");
	    dataSource.setUsername("root");
	    dataSource.setPassword(""); 
	    return dataSource;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);	 
	    sessionBuilder.addAnnotatedClasses(Item.class);
	    sessionBuilder.addAnnotatedClasses(Order.class);
	    sessionBuilder.addAnnotatedClasses(User.class);
	    sessionBuilder.addAnnotatedClasses(UserRole.class);
	 
	    return sessionBuilder.buildSessionFactory();
	}
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    return properties;
	}
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);	 
	    return transactionManager;
	}
}
