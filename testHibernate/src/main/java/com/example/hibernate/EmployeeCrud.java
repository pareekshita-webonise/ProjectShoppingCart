package com.example.hibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class EmployeeCrud {
	public Session sessionStart() {
		Session session = HibernateSessionManager.getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}
	public void insertValues(String name,String address,int age){
		Session session = sessionStart();
		Employee employee = new Employee();
		employee.setEmpName(name);
		employee.setEmpAddress(address);
		employee.setEmpAge(age);
		
		session.save(employee);
		session.getTransaction().commit();
	}
	public void updateName(int id,String name){
		Session session = sessionStart();
		Employee employee = null;
		employee=(Employee)session.get(Employee.class, id);
		employee.setEmpName(name);
		session.update(employee);
		session.getTransaction().commit();
	}
	public void updateAddress(int id,String address){
		Session session = sessionStart();
		Employee employee = null;
		employee=(Employee)session.get(Employee.class, id);
		employee.setEmpAddress(address);
		session.update(employee);
		session.getTransaction().commit();
	}
	public void updateAge(int id,int age){
		Session session = sessionStart();
		Employee employee = null;
		employee=(Employee)session.get(Employee.class, id);
		employee.setEmpAge(age);
		session.update(employee);
		session.getTransaction().commit();
	}
	public void deleteValues(int id){
		Session session = sessionStart();
		Employee employee = null;
		employee=(Employee)session.get(Employee.class, id);
		session.delete(employee);
		session.getTransaction().commit();
	}
	public void displayValues() {
		Session session = sessionStart();
		Query query = session.createQuery("from Employee");
		List<Employee> list = query.list();
		for (int i = 0; i < list.size(); i++) {
			 Employee employee =  list.get(i);
			 System.out.print("Employee ID: "+employee.getEmpId());
			 System.out.print("\tEmployee Name: "+employee.getEmpName());
			 System.out.print("\tEmployee Address: "+employee.getEmpAddress());
			 System.out.print("\tEmployee Age: "+employee.getEmpAge());
			 System.out.println();
		}
		session.getTransaction().commit();
	}
}
