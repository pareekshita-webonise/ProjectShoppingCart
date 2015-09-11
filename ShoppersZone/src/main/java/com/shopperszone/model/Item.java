package com.shopperszone.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.shopperszone.cache.CachableObject;

@Entity
@Table(name = "items")
public class Item implements CachableObject, Serializable {

	private static final long serialVersionUID = -3178299340471670197L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "item_name")
	private String name;

	@Column(name = "price")
	private double price;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "category")
	private String category;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "items")
	private Set<Order> orders = new HashSet<Order>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String getKey() {
		return new Integer(id).toString();
	}

	@Override
	public String getObjectKey() {
		return category;
	}

	public String toString() {
		return this.getId() + " " + this.getName() + " " + this.getCategory() + " " + this.getPrice() + " "
				+ this.getQuantity();
	}
}