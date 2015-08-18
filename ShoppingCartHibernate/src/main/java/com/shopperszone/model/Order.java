package com.shopperszone.model;

import java.util.Date;

public class Order 
{
	private int id;
	private Inventory myCart;
	private User user;
	private Date orderTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Inventory getMyCart() {
		return myCart;
	}

	public void setMyCart(Inventory myCart) {
		this.myCart = myCart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
}
